package pe.com.belatrix.messabel.exercise.service;

import pe.com.belatrix.messabel.exercise.factory.DAOFactory;
import pe.com.belatrix.messabel.exercise.factory.Parametros;
import pe.com.belatrix.messabel.exercise.interfaces.MessageDAO;

public class MessageService {

	DAOFactory objDAOFactory = DAOFactory.getDAOFactory(Parametros.ORIGEN);
	MessageDAO objDAO = objDAOFactory.getMessageDAO();

	public void insertMessage(String message, Integer t) throws Exception{
		objDAO.insertMessage(message, t);
	}
}
