package interfaces;

import javax.ejb.Local;

import entities.Admin;
import entities.Message;
import entities.Request;

@Local
public interface AdminLocal {
	public int Valider (Request m);
	public Admin getAdminbyid(int m);
}
