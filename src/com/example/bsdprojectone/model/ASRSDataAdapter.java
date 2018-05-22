package com.example.bsdprojectone.model;

public class ASRSDataAdapter {
	
	public static String FAKE_STR="[{\"warehouse_in\":\"99\","+
			"\"warehouse_out_all_complete\":\"1\","+
			"\"warehouse_out_floor_one_complete\":\"2\","+
			"\"warehouse_floor_two_people_complete\":\"3\","+
			"\"warehouse_floor_two_mechine_complete\":\"4\","+
			"\"warehouse_out_all_surplus\":\"5\","+
			"\"warehouse_out_floor_one_surplus\":\"7\","+
			"\"warehouse_floor_two_people_surplus\":\"8\","+
			"\"warehouse_floor_two_mechine_surplus\":\"10\""+
			"}]";
	
	
	
	private int warehouse_in;//入库
	private int warehouse_out_all_complete;//已完成出库
	private int warehouse_out_floor_one_complete;//一楼出库已完成
	private int warehouse_floor_two_people_complete;//二楼人工出库已完成
	private int warehouse_floor_two_mechine_complete;//二楼机械手出库已完成
	private int warehouse_out_all_surplus;//出库剩余
	private int warehouse_out_floor_one_surplus;//一楼出库剩余
	private int warehouse_floor_two_people_surplus;//二楼人工出库剩余
	private int warehouse_floor_two_mechine_surplus;//二楼机械手出库剩余
	
	 public ASRSDataAdapter()
	 {
		 
		 /*无参逻辑*/
	 }

	 public ASRSDataAdapter( int warehouse_in,
			  int warehouse_out_all_complete,
			  int warehouse_out_floor_one_complete,
			  int warehouse_floor_two_people_complete,
			  int warehouse_floor_two_mechine_complete,
			  int warehouse_out_all_surplus,
			  int warehouse_out_floor_one_surplus,
			  int warehouse_floor_two_people_surplus,
			  int warehouse_floor_two_mechine_surplus)
	 {
		 this.warehouse_in=warehouse_in;
		 this.warehouse_out_all_complete=warehouse_out_all_complete;
		 this.warehouse_out_floor_one_complete=warehouse_out_floor_one_complete;
		 this.warehouse_floor_two_people_complete=warehouse_floor_two_people_complete;
		 this.warehouse_floor_two_mechine_complete=warehouse_floor_two_mechine_complete;
		 this.warehouse_out_all_surplus=warehouse_out_all_surplus;
		 this.warehouse_out_floor_one_surplus=warehouse_out_floor_one_surplus;
		 this.warehouse_floor_two_people_surplus=warehouse_floor_two_people_surplus;
		 this.warehouse_floor_two_mechine_surplus=warehouse_floor_two_mechine_surplus;
	 }
	
	 public void setIn(int warehouse_in)
	 {
		 this.warehouse_in=warehouse_in;
	 }
	 
	 public int getIn()
	 {
		 return this.warehouse_in;
	 }
	 
	 public void setOutAllComplete(int warehouse_out_all_complete)
	 {
		 this.warehouse_out_all_complete=warehouse_out_all_complete;
	 }
	 
	 public int getOutAllComplete()
	 {
		 return this.warehouse_out_all_complete;
	 }
	 
	 public void setOutAllSurplus(int warehouse_out_all_surplus)
	 {
		 this.warehouse_out_all_surplus=warehouse_out_all_surplus;
	 }
	 
	 public int getOutAllSurplus()
	 {
		 return this.warehouse_out_all_surplus;
	 }
	 
	 public void setOutFloorOneComplete(int warehouse_out_floor_one_complete)
	 {
		 this.warehouse_out_floor_one_complete=warehouse_out_floor_one_complete;
	 }
	 
	 public int getOutFloorOneComplete()
	 {
		 return this.warehouse_out_floor_one_complete;
	 }
	 
	 public void setOutFloorOneSurplus(int warehouse_out_floor_one_surplus)
	 {
		 this.warehouse_out_floor_one_surplus=warehouse_out_floor_one_surplus;
	 }
	 
	 public int getOutFloorOneSurplus()
	 {
		 return this.warehouse_out_floor_one_surplus;
	 }
	 
	 public void setOutFloorTwoPeopleComplete(int warehouse_floor_two_people_complete)
	 {
		 this.warehouse_floor_two_people_complete=warehouse_floor_two_people_complete;
	 }
	 
	 public int getOutFloorTwoPeopleComplete()
	 {
		 return this.warehouse_floor_two_people_complete;
	 }
	 
	 public void setOutFloorTwoPeopleSurplus(int warehouse_floor_two_people_surplus)
	 {
		 this.warehouse_floor_two_people_surplus=warehouse_floor_two_people_surplus;
	 }
	 
	 public int getOutFloorTwoPeopleSurplus()
	 {
		 return this.warehouse_floor_two_people_surplus;
	 }
	 
	 public void setOutFloorTwoMechineComplete(int warehouse_floor_two_mechine_complete)
	 {
		 this.warehouse_floor_two_mechine_complete=warehouse_floor_two_mechine_complete;
	 }
	 
	 public int getOutFloorTwoMechineComplete()
	 {
		 return this.warehouse_floor_two_mechine_complete;
	 }
	 
	 public void setOutFloorTwoMechineSurplus(int warehouse_floor_two_mechine_surplus)
	 {
		 this.warehouse_floor_two_mechine_surplus=warehouse_floor_two_mechine_surplus;
	 }
	 
	 public int getOutFloorTwoMechineSurplus()
	 {
		 return this.warehouse_floor_two_mechine_surplus;
	 }
	 public String toString()
	 {
		 return this.warehouse_in+" "+
				 this.warehouse_out_all_complete+" "+
				 this.warehouse_out_floor_one_complete+" "+
				 this.warehouse_floor_two_people_complete+" "+
				 this.warehouse_floor_two_mechine_complete+" "+
				 this.warehouse_out_all_surplus+" "+
				 this.warehouse_out_floor_one_surplus+" "+
				 this.warehouse_floor_two_people_surplus+" "+
				 this.warehouse_floor_two_mechine_surplus+"\n";
	 }
}
