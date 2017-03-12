public enum Party {
    //All parties possible
    REP(), DEM(), LIB(), GRN(), CNS(), TEA(), ARR(), UNK();

    /**
     * Converts from string to Party enum
     * @param s the string that is being converted to a Party enum
     * @return a Party enum
     */
    public static Party fromString(String s){
        //Convert passed in string to lowercase and check for match
        switch(s.toUpperCase()) {
            case "REP": return REP;
            case "DEM": return DEM;
            case "LIB": return LIB;
            case "GRN": return GRN;
            case "CNS": return CNS;
            case "TEA": return TEA;
            case "ARR": return ARR;
            default:    return UNK;
        }
    }

    /**
     * Converts a Party enum to a string
     * @return a String representation of a Party enum
     */
    @Override
    public String toString(){
        //Convert passed in string to lowercase adn check for match
        switch(this) {
            case REP:   return "REP";
            case DEM:   return "DEM";
            case LIB:   return "LIB";
            case GRN:   return "GRN";
            case CNS:   return "CNS";
            case TEA:   return "TEA";
            case ARR:   return "ARR";
            default:    return "UNK";
        }
    }
}
