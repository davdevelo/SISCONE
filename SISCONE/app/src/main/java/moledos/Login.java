package moledos;

/**
 * Created by user on 06/12/2015.
 */
public class Login {

    private String cedula;
    private String contrasena;
    private String tipo;

    public Login() {
    }

    public Login(String cedula, String contrasena, String tipo) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login = (Login) o;

        if (cedula != null ? !cedula.equals(login.cedula) : login.cedula != null) return false;
        if (contrasena != null ? !contrasena.equals(login.contrasena) : login.contrasena != null)
            return false;
        return !(tipo != null ? !tipo.equals(login.tipo) : login.tipo != null);

    }

    @Override
    public int hashCode() {
        int result = cedula != null ? cedula.hashCode() : 0;
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }
}
