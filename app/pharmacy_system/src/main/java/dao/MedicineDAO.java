package dao;

import model.Medicine;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {
    private List<Medicine> medicines = new ArrayList<>();
    private int nextId = 1;

    public boolean addMedicine(Medicine medicine) {
        medicine.setId(nextId++);
        return medicines.add(medicine);
    }

    public boolean updateMedicine(Medicine medicine) {
        for (int i = 0; i < medicines.size(); i++) {
            if (medicines.get(i).getId() == medicine.getId()) {
                medicines.set(i, medicine);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMedicine(int medicineId) {
        return medicines.removeIf(med -> med.getId() == medicineId);
    }

    public Medicine getMedicine(int medicineId) {
        for (Medicine med : medicines) {
            if (med.getId() == medicineId) {
                return med;
            }
        }
        return null;
    }

    public List<Medicine> listAllMedicines() {
        return new ArrayList<>(medicines);
    }
}