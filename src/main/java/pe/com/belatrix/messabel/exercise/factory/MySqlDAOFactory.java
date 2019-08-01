package pe.com.belatrix.messabel.exercise.factory;

import pe.com.belatrix.messabel.exercise.interfaces.MessageDAO;
import pe.com.belatrix.messabel.exercise.repository.MySqlMessageDAO;

public class MySqlDAOFactory extends DAOFactory{

	@Override
	public MessageDAO getMessageDAO() {
		return new MySqlMessageDAO();
	}
		
}
