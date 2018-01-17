package rest.webapi.laptopbag.helpers;

import java.util.Arrays;

import rest.webapi.laptopbag.pojos.Features;
import rest.webapi.laptopbag.pojos.Laptop;

public class PojoHelper {
	/*
	 * 1. Accepts 3 args:
	 * 		 BrandName, LaptopName,featuresOfLaptop
	 * 2. ID is internally random generated
	 * 3. Using argument data and Id build a new Laptop object then return
	 * from a method
	 */
	public static Laptop buildNewLaptop(String brand,String laptopName,String featuresOfLaptop){
		int id=(int)(1000*(Math.random()));
		Laptop laptop=new Laptop();
		laptop.setBrandName(brand);
		laptop.setLaptopName(laptopName);
		laptop.setId(id);		
//		Features features=new Features();
//		features.setFeature( Arrays.asList(featuresOfLaptop.split(",")) );
//		laptop.setFeatures(features);
		
//		Features features=new Features(Arrays.asList(featuresOfLaptop.split(",")));
//		laptop.setFeatures(features);		
//		laptop.setFeatures(new Features(Arrays.asList(featuresOfLaptop.split(","))));
		
		return laptop;		
		
	}
}
