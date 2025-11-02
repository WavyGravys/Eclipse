package util;

public enum Numbers {
    BYTE(Byte::parseByte, Byte.class),
    SHORT(Short::parseShort, Short.class),
    INT(Integer::parseInt, Integer.class),
    LONG(Long::parseLong, Long.class),
    FLOAT(Float::parseFloat, Float.class),
    DOUBLE(Double::parseDouble, Double.class);
	
    private final Function<String, ? extends Number> parser;
    private final Class<? extends Number> numberClass;

    public Class<? extends Number> getNumberClass() {
        return numberClass;
    }
    
    Numbers(Function<String, ? extends Number> parser,
    	 Class<? extends Number> numberClass) {
        this.parser = parser;
        this.numberClass = numberClass;
    }
    
    private Number _parse(String numberString){
        return parser.apply(numberString);
    }
    
    public static <T extends Number> T parseAs(String numberString, Numbers type) {
    	// Safe cast: numberClass always matches the Number subtype that parser returns
    	@SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) type.numberClass;
    	return clazz.cast(type._parse(numberString));
    }
    
    public static Numbers getType(Number number) {
    	for (Numbers type : values()) {
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
    public static Number parse(String numberString) {
        Numbers[] parseOrder = {BYTE, SHORT, INT, LONG, FLOAT, DOUBLE};
        
        NumberFormatException lastException = null;
        
        for (Numbers type : parseOrder) {
            try {
                return type._parse(numberString);
            } catch (NumberFormatException e) {
                lastException = e;
            }
        }
        
        throw new NumberFormatException("Could not parse '" + numberString 
            + "' as any numeric type: " + lastException.getMessage());
    }
}