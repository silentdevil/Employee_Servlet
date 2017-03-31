package com.exist.employee;

public class Address{
   private long addressId;
   private int streetno;
   private String street; 
   private String brgy;    
   private String city;     
   private String zipcode; 

   public long getAddressId() {
      return addressId;
   }

   public void setAddressId(long addressId) {
      this.addressId = addressId;
   }

   public int getStreetno() {
      return streetno;
   }

   public void setStreetno(int streetno) {
      this.streetno = streetno;
   }

   public String getStreet() {
      return street;
   }
   public void setStreet( String street ) {
      this.street = street;
   }
   public String getCity() {
      return city;
   }
   public void setCity( String city ) {
      this.city = city;
   }
   public String getBrgy() {
      return brgy;
   }
   public void setBrgy(String brgy) {
      this.brgy = brgy;
   }
   public String getZipcode() {
      return zipcode;
   }
   public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
   }
   
}