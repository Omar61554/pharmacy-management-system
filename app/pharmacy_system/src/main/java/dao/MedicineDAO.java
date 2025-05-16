package dao; 
import model.Medicine;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicineDAO {
    // In-memory database using a map where key is medicine ID and value is Medicine object
    private Map<Integer, Medicine> medicineDatabase;
    private int nextId; // To auto-generate IDs for new medicines

    public static void main(String[] args){
        Medicine m1 = new Medicine("test",1.2, new Date(), 2);
        Medicine m2 = new Medicine("test",1.2, new Date(), 2);
        MedicineDAO data = new MedicineDAO();
        data.addMedicine(m1);
        
        data.update(1,-1);
        data.addMedicine(m2);
        System.out.println(data.findById(1).getDetails());
    }

    public MedicineDAO() {
        this.medicineDatabase = new HashMap<>();
        this.nextId = 1; // Start IDs from 1
    }

    /**
     * Saves a new medicine to the database
     * @param medicine The medicine to be saved
     */
    public void addMedicine(String name, double price, Date expiration, int quantity) {
        Medicine medicine = new Medicine(name,price,expiration,quantity);
        if (medicineDatabase.containsValue(medicine)){
            System.out.println("already exists");
        }
        medicine.setId(nextId++);
        medicineDatabase.put(medicine.getId(), medicine);

    }
    
    public void addMedicine(Medicine medicine) {
        if (medicine == null) {
            throw new IllegalArgumentException("Medicine cannot be null");
        }
        
        if (medicineDatabase.containsValue(medicine.getName())){
            System.out.println("already exists");
        }
        // Assign an ID if it's a new medicine
        if (medicine.getId() == 0) {
            medicine.setId(nextId++);
        }
        medicineDatabase.put(medicine.getId(), medicine);
    }

    //  public void save(Medicine medicine) {
    //     
    //     }
        
    //     medicineDatabase.put(medicine.getId(), medicine);
    // }

    /**
     * Updates an existing medicine in the database
     * @param medicine The medicine with updated information
     */
    public void update(int id, int quantity) {
        if (!medicineDatabase.containsKey(id)) {
            throw new IllegalArgumentException("Medicine with ID " + id + " not found");
        }

        medicineDatabase.get(id).updateQuantity(quantity);
        // medicineDatabase.put(medicine.getId(), medicine);
    }

    /**
     * Deletes a medicine from the database
     * @param id The ID of the medicine to delete
     */
    public void delete(int id) {
        if (!medicineDatabase.containsKey(id)) {
            throw new IllegalArgumentException("Medicine with ID " + id + " not found");
        }
        
        medicineDatabase.remove(id);
    }

    /**
     * Finds a medicine by its ID
     * @param id The ID of the medicine to find
     * @return The found medicine, or null if not found
     */
    public Medicine findById(int id) {
        return medicineDatabase.get(id);
    }

    /**
     * Retrieves all medicines in the database
     * @return A list of all medicines
     */
    public List<Medicine> findAll() {
        return new ArrayList<>(medicineDatabase.values());
    }
}