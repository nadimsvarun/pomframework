package com.qa.opencart.constant;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final int DEFAULT_SHORT_TIMEOUT=5;
	public static final int DEAFAULT_MEDIUM_TIMEOUT=10;
	public static final int DEFAULT_LONG_TIMEOUT = 20;
	
	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String LOGIN_PAGE_URL_CONTAINS ="route=account/login";
	public static final String ACCOUNT_PAGE_TITLE ="My Account";
	public static final String ACCOUNT_PAGE_URL_CONTAINS ="route=account/account";
	public static final Object ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
    public static final String SHEET_NAME = "register";
}
