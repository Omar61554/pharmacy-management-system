package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Medicine;

/**
 * Data Access Object (DAO) for managing Medicine objects.
 * This class handles the persistence of Medicine data to a file.
 */
public class MedicineDAO implements Serializable {
    // The name of the file where medicine data is stored
    private static final String FILE_NAME = "medicines.dat";
    // List to hold all Medicine objects in memory
    private List<Medicine> medicines = new ArrayList<>();

    /**
     * Constructs a new MedicineDAO.
     * When initialized, it attempts to load existing medicine data from the file.
     */
    public MedicineDAO() {
        loadMedicines();
    }

    /**
     * Adds a new medicine to the list and saves the updated list to the file.
     *
     * @param medicine The Medicine object to add.
     * @return true if the medicine was added successfully, false otherwise.
     */
    public boolean addMedicine(Medicine medicine) {
        boolean added = medicines.add(medicine);
        if (added) saveMedicines();
        return added;
    }

    /**
     * Updates an existing medicine in the list and saves the updated list to the file.
     * The medicine is identified by its ID.
     *
     * @param medicine The Medicine object with updated information.
     * @return true if the medicine was found and updated, false otherwise.
     */
    public boolean updateMedicine(Medicine medicine) {
        for (int i = 0; i < medicines.size(); i++) {
            if (medicines.get(i).getId() == medicine.getId()) {
                medicines.set(i, medicine);
                saveMedicines();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a medicine from the list based on its ID and saves the updated list to the file.
     *
     * @param medicineId The ID of the medicine to delete.
     * @return true if a medicine with the specified ID was found and removed, false otherwise.
     */
    public boolean deleteMedicine(int medicineId) {
        boolean removed = medicines.removeIf(med -> med.getId() == medicineId);
        if (removed) saveMedicines();
        return removed;
    }

    /**
     * Retrieves a medicine from the list based on its ID.
     *
     * @param medicineId The ID of the medicine to retrieve.
     * @return The Medicine object if found, or null if no medicine with the specified ID exists.
     */
    public Medicine getMedicine(int medicineId) {
        for (Medicine med : medicines) {
            if (med.getId() == medicineId) {
                return med;
            }
        }
        return null;
    }

    /**
     * Returns a copy of the list of all medicines currently stored.
     *
     * @return A new ArrayList containing all Medicine objects.
     */
    public List<Medicine> listAllMedicines() {
        return new ArrayList<>(medicines);
    }

    /**
     * Saves the current list of medicines to the data file using serialization.
     * If an IOException occurs during saving, the stack trace is printed.
     */
    private void saveMedicines() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(medicines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of medicines from the data file using deserialization.
     * If the file does not exist, the method returns without loading.
     * If an exception occurs during loading (e.g., file not found, class not found),
     * the stack trace is printed.
     */
    @SuppressWarnings("unchecked")
    private void loadMedicines() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // Return if file doesn't exist
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            medicines = (List<Medicine>) ois.readObject();
        } catch (Exception e) { // Catching a general Exception to handle IOException and ClassNotFoundException
            e.printStackTrace();
        }
    }
}
