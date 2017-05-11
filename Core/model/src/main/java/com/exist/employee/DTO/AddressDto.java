package com.exist.employee;

public class AddressDto {

   
   private String streetNo;
   private String street; 
   private String brgy;    
   private String city;     
   private String zipcode; 

  
   public String getStreetNo() {
      return streetNo;
   }

   public AddressDto setStreetNo(String streetNo) {
      this.streetNo = streetNo;
      return this;
   }

   public String getStreet() {
      return street;
   }
   public AddressDto setStreet( String street ) {
      this.street = street;
      return this;
   }
   public String getCity() {
      return city;
   }
   public AddressDto setCity( String city ) {
      this.city = city;
      return this;
   }
   public String getBrgy() {
      return brgy;
   }
   public AddressDto setBrgy(String brgy) {
      this.brgy = brgy;
      return this;
   }
   public String getZipcode() {
      return zipcode;
   }
   public AddressDto setZipcode(String zipcode) {
      this.zipcode = zipcode;
      return this;
   }
   
   public String toString() {
	   StringBuffer sb = new StringBuffer();
		sb.append(streetNo).append(" ");
		sb.append(street).append(", ");
		sb.append(brgy).append(", ");
		sb.append(city).append(" " + zipcode);
		return sb.toString();
   }

   @Override
   public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

         AddressDto add2 = (AddressDto) obj;

         return this.streetNo == add2.getStreetNo() && this.street.equals(add2.getStreet()) && this.brgy.equals(add2.getBrgy())
            && this.city.equals(add2.getCity());
         //return this.toString().equals(obj.toString());
   }

   @Override
   public int hashCode() {
        return java.util.Objects.hash(streetNo,street,brgy,city);
    }
   

   
}
