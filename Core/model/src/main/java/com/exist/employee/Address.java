package com.exist.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Cacheable;
import org.hibernate.annotations.*;

@Entity
@Table(name = "addresses")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address {
	@Id
	@GeneratedValue
   @Column(name = "addressid")
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
   
   public String toString() {
	   StringBuffer sb = new StringBuffer();
		sb.append(streetno).append(" ");
		sb.append(street).append(", ");
		sb.append(brgy).append(", ");
		sb.append(city).append(" " + zipcode);
		return sb.toString();
   }

   @Override
   public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

         Address add2 = (Address) obj;

         return this.streetno == add2.getStreetno() && this.street.equals(add2.getStreet()) && this.brgy.equals(add2.getBrgy())
            && this.city.equals(add2.getCity());
         //return this.toString().equals(obj.toString());
   }

   @Override
   public int hashCode() {
        return java.util.Objects.hash(streetno,street,brgy,city);
    }
   

   
}
