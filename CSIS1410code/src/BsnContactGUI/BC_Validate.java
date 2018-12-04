///**
// * BC_Validate
// * Regular expressions
// * 	to validate business contact data fields
// */
//
///**
// * @author rbaird
// */
//package BsnContactGUI;
//
//public class BC_Validate
//{
//	public static final String FIRST_NAME_PATTERN = "^[A-Z][a-zA-Z]*$";
//	public static final String LAST_NAME_PATTERN = "^[a-zA-Z]+([ '-][a-zA-Z]+)*$";
//	//public static final String ADDRESS_PATTERN = "\\d+\\s+(([a-zA-Z]\\.)+|([a-zA-Z]\\.)+\\s([a-zA-Z]+)";
//	public static final String ADDRESS_PATTERN = "^[a-zA-Z\\d]+(([\\'\\,\\.\\- #][a-zA-Z\\d ])?[a-zA-Z\\d]*[\\.]*)*$";
//	public static final String CITY_PATTERN = "^([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)*$";
//	public static final String STATE_PATTERN = "^([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)$";
//	public static final String ZIP_PATTERN = "(^\\d{5}$)|(^\\d{5}-\\d{4}$)";
//	//public static final String PHONE_PATTERN = "[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}";
//	public static final String PHONE_PATTERN = "^(\\(?\\d\\d\\d\\)?)?( |-|\\.)?\\d\\d\\d( |-|\\.)?\\d{4,4}(( |-|\\.)?[ext\\.]+ ?\\d+)?$";
//	//public static final String EMAIL_PATTERN = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})";
//	public static final String COMPANY_PATTERN = "^\\w[\\w.'-]+$";
//	//public static final String EMAIL_PATTERN = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}";
//	public static final String EMAIL_PATTERN = "([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})";
//	public static final String DATE_PATTERN = "^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d$";
//
//	/**
//	 * @param firstName
//	 * @return true if firstName matches FIRST_NAME_PATTERN
//	 */
//	public static boolean isValidFirstName(String firstName)
//	{
//		return firstName.matches(FIRST_NAME_PATTERN);
//	}
//
//	/**
//	 * @param lastName
//	 * @return true if lastName matches LAST_NAME_PATTERN
//	 */
//	public static boolean isValidLastName(String lastName)
//	{
//		return lastName.matches(LAST_NAME_PATTERN);
//	}
//
//	/**
//	 * @param address
//	 * @return true if address matches ADDRESS_PATTERN
//	 */
//	public static boolean isValidAddress(String address)
//	{
//		return address.matches(ADDRESS_PATTERN);
//	}
//
//	/**
//	 * @param city
//	 * @return true if city matches CITY_PATTERN
//	 */
//	public static boolean isValidCity(String city)
//	{
//		return city.matches(CITY_PATTERN);
//	}
//
//	/**
//	 * @param state
//	 * @return true if state matches STATE_PATTERN
//	 */
//	public static boolean isValidState(String state)
//	{
//		return state.matches(STATE_PATTERN);
//	}
//
//	/**
//	 * @param zipcode
//	 * @return true if zipcode matches ZIP_PATTERN
//	 */
//	public static boolean isValidZipcode(String zipcode)
//	{
//		return zipcode.matches(ZIP_PATTERN);
//	}
//
//	/**
//	 * @param phone
//	 * @return true if phone matches PHONE_PATTERN
//	 */
//	public static boolean isValidPhone(String phone)
//	{
//		return phone.matches(PHONE_PATTERN);
//	}
//
//	/**
//	 * @param company
//	 * @return true if email matches COMPANY_PATTERN
//	 */
//	public static boolean isValidCompanyName(String companyName)
//	{
//		return companyName.matches(COMPANY_PATTERN);
//	}
//
//	/**
//	 * @param email
//	 * @return true if email matches EMAIL_PATTERN
//	 */
//	public static boolean isValidEmail(String email)
//	{
//		return email.matches(EMAIL_PATTERN);
//	}
//
//	/**
//	 * @param date
//	 * @return true if date matches DATE_PATTERN
//	 */
//	public static boolean isValidDate(String date)
//	{
//		return date.matches(DATE_PATTERN);
//	}
//
//}
