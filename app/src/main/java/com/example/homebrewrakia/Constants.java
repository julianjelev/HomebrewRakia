package com.example.homebrewrakia;

public final class Constants {
    public static final int REQUEST_CODE_CORRECTION_ACTIVITY_FOR_SOURCE_DEGREE = 1;
    public static final int REQUEST_CODE_CORRECTION_ACTIVITY_FOR_DILUENT_DEGREE = 2;
    public static final int REQUEST_CODE_SUGAR_CORRECTION_ACTIVITY_FOR_SOURCE_DEGREE = 3;

    public static final String EXTRA_RESULT = "result";
    public static final String EXTRA_INPUT_VALUE = "inputValue";
    public static final String EXTRA_RETURN_TO_CALLER = "returnToCaller";
    public static final int DATA_SOURCE_TABLE_ETHANOL = 1;
    public static final int DATA_SOURCE_TABLE_SUGAR_GRAVITY = 2;
    public static final int DATA_SOURCE_TABLE_SUGAR_BRIX = 3;
    //
    // PRIVATE //

    /**
     The caller references the constants using <tt>Constants.EMPTY_STRING</tt>,
     and so on. Thus, the caller should be prevented from constructing objects of
     this class, by declaring this private constructor.
     */
    private Constants(){
        //this prevents even the native class from
        //calling this constructor as well :
        throw new AssertionError();
    }
}