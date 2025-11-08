package util;

public enum Types {
	STRING(s -> s, String.class, n -> {return new String[n]; }),
	BYTE(Byte::parseByte, byte.class,  n -> { return new Byte[n]; }),
    SHORT(Short::parseShort, short.class, n -> { return new Short[n]; }),
    INT(Integer::parseInt, int.class, n -> { return new Integer[n]; }),
    LONG(Long::parseLong, long.class, n -> { return new Long[n]; }),
    FLOAT(Float::parseFloat, float.class, n -> { return new Float[n]; }),
    DOUBLE(Double::parseDouble, double.class, n -> { return new Double[n]; });
	
	
	
	private final Function<String, ?> parser;
	private final Class<?> typeClass;
	private final Function<Integer, Object> arrayBuilder;
    
    <T> Types(Function<String, T> parser,
    		Class<T> typeClass,
    		Function<Integer, Object> arrayBuilder) {
    	
        this.parser = parser;
        this.typeClass = typeClass;
        this.arrayBuilder = arrayBuilder;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T parse(String string) {
        return (T) parser.apply(string);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T[] parseArray(String[] strings) {
    	Object[] array = (Object[]) newArray(strings.length);
    	
    	for (int i = 0; i < strings.length; i++) {
        	array[i] = parser.apply(strings[i]);
        }
    	
    	return (T[]) array;
    }
    
    @SuppressWarnings("unchecked")
    public <T> Class<T> getTypeClass() {
        return (Class<T>) typeClass;
    }
    
	public Object newArray(int length) {
        return arrayBuilder.apply(length);
    }
}