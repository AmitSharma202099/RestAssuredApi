package FakeUserAPITests;

import FakeUserAPITests.FakeUser.Address.AddressBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FakeUser {
	
	private String email;
	private String username;
	private String password;
	private String phone;
	private Name name;
	private Address address;
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Name {
		private String firstname;
		private String lastname;
	}
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Address {
		private String city;
		private String street;
		private int number;
		private String zipcode;
		private Geolocation geolocation;  // imp to create class ref
		
		@Data
		@Builder
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Geolocation {
			private String lat;
			private String longitude;
		}
	}
	
}
