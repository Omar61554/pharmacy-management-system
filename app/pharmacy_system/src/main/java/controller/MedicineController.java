package controller;

import model.Medicine;
import dao.MedicineDAO;
import java.util.List;

public class MedicineController {
    private MedicineDAO medicineDAO;

    public MedicineController(MedicineDAO medicineDAO) {
        this.medicineDAO = medicineDAO;
    }

    public boolean addMedicine(Medicine medicine) {
        return medicineDAO.addMedicine(medicine);
    }

    public boolean updateMedicine(Medicine medicine) {
        return medicineDAO.updateMedicine(medicine);
    }

    public boolean deleteMedicine(int medicineId) {
        return medicineDAO.deleteMedicine(medicineId);
    }

    public Medicine getMedicine(int medicineId) {
        return medicineDAO.getMedicine(medicineId);
    }

    public List<Medicine> listAllMedicines() {
        return medicineDAO.listAllMedicines();
    }
}