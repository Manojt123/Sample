package interviewQsn;

public class Car {

	protected int numberOfSeats = 0;
	 
    public Car(int i_NumberOfSeats) {
        super();
        //perform other initialization here
        numberOfSeats = i_NumberOfSeats;
    }
 
    public void setLicensePlate(String license) {
        super.setLicensePlate(license);
    }
 
    public String toString() {
        String licensePlate = null;
		return "The car has " + numberOfSeats + " seats. Its license is " + licensePlate;
    }
 
    public int getNumberOfSeats() {
        return this.numberOfSeats;
    }
}
