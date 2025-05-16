package dao;

import model.Medicine;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO implements Serializable {
    private static final String FILE_NAME = "medicines.dat";
    private List<Medicine> medicines = new ArrayList<>();

    public MedicineDAO() {
        loadMedicines();
    }

    public boolean addMedicine(Medicine medicine) {
        boolean added = medicines.add(medicine);
        if (added) saveMedicines();
        return added;
    }

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

    public boolean deleteMedicine(int medicineId) {
        boolean removed = medicines.removeIf(med -> med.getId() == medicineId);
        if (removed) saveMedicines();
        return removed;
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

    private void saveMedicines() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(medicines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadMedicines() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            medicines = (List<Medicine>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}