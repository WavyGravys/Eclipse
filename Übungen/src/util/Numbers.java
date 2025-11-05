package util;


public enum Numbers {
    BYTE(Byte::parseByte, Byte.class, n -> { return new byte[n]; }),
    SHORT(Short::parseShort, Short.class, n -> { return new short[n]; }),
    INT(Integer::parseInt, Integer.class, n -> { return new int[n]; }),
    LONG(Long::parseLong, Long.class, n -> { return new long[n]; }),
    FLOAT(Float::parseFloat, Float.class, n -> { return new float[n]; }),
    DOUBLE(Double::parseDouble, Double.class, n -> { return new double[n]; });
	
    private final Function<String, ? extends Number> parser;
    public final Class<? extends Number> numberClass;
    private final Function<Integer, Object> arrayBuilder;
    
    
    Numbers(Function<String, ? extends Number> parser,
    		Class<? extends Number> numberClass,
    		Function<Integer, Object> arrayBuilder) {
    	
        this.parser = parser;
        this.numberClass = numberClass;
        this.arrayBuilder = arrayBuilder;
    }
    
    @SuppressWarnings("unchecked")
	public static <T extends Number> T parseAs(String numberString, Numbers type) {
    	return (T) type.parser.apply(numberString);
    }
    
    public static Numbers getType(Number number) {
    	for (Numbers type : values()) { // ??
    		if (type.numberClass == number.getClass()) {
    			return type;
    		}
    	}
    	throw new IllegalArgumentException("Unsupported Number type: "
    									  + number.getClass());
    }
    
    /**
     * Attempts to parse a string as a Number, trying types in order of specificity.
     * Tries: INTEGER -> LONG -> DOUBLE -> FLOAT -> SHORT -> BYTE
     * @param numberString the string to parse
     * @return the parsed Number
     * @throws NumberFormatException if the string cannot be parsed as any number type
     */
    public static <T extends Number> T parse(String numberString) {
    	Numbers[] parseOrder = {BYTE, SHORT, INT, LONG, FLOAT, DOUBLE};
        
        NumberFormatException lastException = null;
        
        for (Numbers type : parseOrder) {
            try {
                return Numbers.parseAs(numberString, type);
            } catch (NumberFormatException e) {
                lastException = e;
            }
        }
        
        throw new NumberFormatException("Could not parse '" + numberString 
            + "' as any numeric type: " + lastException.getMessage());
    }
    
    public Object getPrimitiveArrayObject(int n) {
        return arrayBuilder.apply(n);
    }
}