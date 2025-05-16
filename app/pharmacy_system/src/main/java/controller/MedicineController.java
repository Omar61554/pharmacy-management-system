package controller;

import java.util.List;

import dao.MedicineDAO;
import model.Medicine;

/**
 * Controller class for managing Medicine-related operations.
 * This class acts as an intermediary between the presentation layer and the data access layer (DAO).
 */
public class MedicineController {
    // Data Access Object for Medicine
    private MedicineDAO medicineDAO;

    /**
     * Constructs a new MedicineController with a specified MedicineDAO.
     *
     * @param medicineDAO The MedicineDAO instance to be used by this controller.
     */
    public MedicineController(MedicineDAO medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    /**
     * Adds a new medicine to the system.
     *
     * @param medicine The Medicine object to add.
     * @return true if the medicine was added successfully, false otherwise.
     */
    public boolean addMedicine(Medicine medicine) {
        return medicineDAO.addMedicine(medicine);
    }

    /**
     * Updates an existing medicine in the system.
     *
     * @param medicine The Medicine object with updated information.
     * @return true if the medicine was updated successfully, false otherwise.
     */
    public boolean updateMedicine(Medicine medicine) {
        return medicineDAO.updateMedicine(medicine);
    }

    /**
     * Deletes a medicine from the system based on its ID.
     *
     * @param medicineId The ID of the medicine to delete.
     * @return true if the medicine was deleted successfully, false otherwise.
     */
    public boolean deleteMedicine(int medicineId) {
        return medicineDAO.deleteMedicine(medicineId);
    }

    /**
     * Retrieves a medicine from the system based on its ID.
     *
     * @param medicineId The ID of the medicine to retrieve.
     * @return The Medicine object if found, or null otherwise.
     */
    public Medicine getMedicine(int medicineId) {
        return medicineDAO.getMedicine(medicineId);
    }

    /**
     * Lists all medicines currently in the system.
     *
     * @return A list of all Medicine objects.
     */
    public List<Medicine> listAllMedicines() {
        return medicineDAO.listAllMedicines();
    }
}
