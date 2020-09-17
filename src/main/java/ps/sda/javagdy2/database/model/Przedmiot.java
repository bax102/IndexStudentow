package ps.sda.javagdy2.database.model;

public enum Przedmiot {
    J_POLSKI("Język Polski"),
    J_ANGIELSKI ("Język Angielski"),
    INFORMATYKA ("Języki programowania"),
    MATEMATYKA (" Matematyka"),
    RELIGIA ("Religia");

    private String opis;

    Przedmiot(String opis) {
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }
}
