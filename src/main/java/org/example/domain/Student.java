package org.example.domain;

import org.example.repository.HasID;

public class Student implements HasID<String> {
    private String idStudent;
    private String nume;
    private int grupa;
    private String email;

    private String numeProf;

    /**
     * Class Constructor
     * @param idStudent - id-ul studentului
     * @param nume - numele studentului
     * @param grupa - grupa studentului
     * @param email - emailul unui student
     */
    public Student(String idStudent, String nume, int grupa, String email, String numeProf) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.grupa = grupa;
        this.email = email;
        this.numeProf = numeProf;
    }

    /**
     * @return id-ul unui student
     */
    public String getID() {
        return idStudent;
    }

    /**
     * modifica id-ul unui student
     * @param ID - noul id al studentului
     */
    public void setID(String ID) {
        this.idStudent = ID;
    }

    /**
     * @return numele studentului
     */
    public String getNume() {
        return nume;
    }

    /**
     * modifica numele studentului
     * @param nume - noul nume
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * @return grupa studentului
     */
    public int getGrupa() {
        return grupa;
    }

    /**
     * modifica grupa studentului
     * @param grupa - noua grupa
     */
    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    /**
     * @return emai-ul studentului
     */
    public String getEmail() {
        return email;
    }

    /**
     * modifica emailul studentului
     * @param email - noul email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeProf() {
        return numeProf;
    }

    public void setNumeProf(String numeProf) {
        this.numeProf = numeProf;
    }

    /**
     * @return un obiect de tip Student sub forma de string
     */


    @Override
    public String toString() {
        return idStudent + "," + nume + "," + grupa + "," + email + "," +numeProf;
    }
}
