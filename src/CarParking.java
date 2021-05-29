public class CarParking {
	private Slot[] slots;
	public CarParking(Integer n)
	{
		this.slots=new Slot[n];
		for(int i=0;i<slots.length;i++){
			Slot slot=new Slot(i+1);
			slots[i]=slot;

		}
		System.out.println("Created parking of "+n+" slots");
	}
	public Slot[] getSlots() {
		return slots;
	}

	public void setSlots(Slot[] slots) {
		this.slots = slots;
	}
	public void perform(String command){
		if(command.isEmpty()){
			System.out.println("Command is Empty");
		}
		String[] params=command.split(" ");
		switch(params[0]){
			case "Create_parking_lot":
				System.out.println("Cannot initialize car parking lot now.");
				break;
	
			case "Park":
				if(validate(params[1])==true && params[2].compareTo("driver_age")==0){
					assign(params[1],Integer.parseInt(params[3]));
				}
				else{
					System.out.println("Car details are not valid....Moving to next command");
				}
				break;
			case "Slot_numbers_for_driver_of_age":
				printSlotNumbersWithDriverAge(Integer.parseInt(params[1]));
				break;
			case "Slot_number_for_car_with_number":
				printSlotNumberWithCarNumber(params[1]);
				break;
			case "Leave":
				leave(Integer.parseInt(params[1]));
				break;
			case "Vehicle_registration_number_for_driver_of_age":
				printVehicleNumbersWithDriverAge(Integer.parseInt(params[1]));
				break;
			default:
				System.out.println("Error....! The command entered is incorrect!");
		}

	}
	private void printVehicleNumbersWithDriverAge(Integer age) {
		StringBuilder vehicleNumbers = new StringBuilder();
		for(int i=0;i<slots.length;i++){
			if(slots[i].getDriverAge()==null){
				continue;
			}
			if(slots[i].getDriverAge().equals(age)){
				if(vehicleNumbers.toString().equals("")){
					vehicleNumbers.append(slots[i].getRegistrationNumberPlate());
					continue;
				}
				vehicleNumbers.append(",").append(slots[i].getRegistrationNumberPlate());	
			}
		}
		if(vehicleNumbers.toString().equals("")){
			System.out.println("No parked car matches the query");
			return;
		}
		System.out.println(vehicleNumbers.toString());

	}
	private void leave(Integer slotNumber) {
		if((slotNumber-1)>=slots.length){
			System.out.println("Error....slot number cannot be greater than number of slots");
			return;
		}
		slots[slotNumber-1].setDriverAge(null);
		slots[slotNumber-1].setRegistrationNumberPlate(null);
		System.out.println(slotNumber);		
	}
	private void printSlotNumberWithCarNumber(String carNumber) {
		if(validate(carNumber)){
			for(int i=0;i<slots.length;i++){
				if(slots[i].getRegistrationNumberPlate()== null){
					continue;
				}
				if(slots[i].getRegistrationNumberPlate().equals(carNumber))
				{
					System.out.println("Car with vehicle registration number "+carNumber+" is parked at slot number "+slots[i].getSlotNumber());
					return;
				}
			}
		}
		System.out.println("No parked car matches the query");

	}
	private void printSlotNumbersWithDriverAge(Integer age) {
		StringBuilder commaSeperatedSlotNumbers= new StringBuilder();
		for(int i=0;i<slots.length;i++){
			if(slots[i].getDriverAge()==null){
				continue;
			}
			if(slots[i].getDriverAge().equals(age)){
				if(commaSeperatedSlotNumbers.toString().equals("")){
					commaSeperatedSlotNumbers.append(slots[i].getSlotNumber().toString());
				}
				else
				{
					commaSeperatedSlotNumbers.append(",").append(slots[i].getSlotNumber().toString());
				}
			}
		}
		if(commaSeperatedSlotNumbers.toString().equals("")){
			System.out.println("No slots are filled with drivers of age "+age);
			return;
		}
		System.out.println(commaSeperatedSlotNumbers.toString());

	}
	private void assign(String carNumber, Integer driverAge) {
		for(int i=0;i<slots.length;i++){
			if(slots[i].getRegistrationNumberPlate()==null){
				slots[i].setDriverAge(driverAge);
				slots[i].setRegistrationNumberPlate(carNumber);
				System.out.println("Car with vehicle registration number "+carNumber+" has been parked at slot number "+slots[i].getSlotNumber());
				return;
			}
		}
		System.out.println("Parking lot is full!.... Moving to next command");
	}
	private boolean validate(String carNumber) {
		return carNumber.length()==13?true:false;
	}


}
