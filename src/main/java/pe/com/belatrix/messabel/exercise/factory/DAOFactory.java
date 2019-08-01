package pe.com.belatrix.messabel.exercise.factory;

import pe.com.belatrix.messabel.exercise.interfaces.MessageDAO;

public abstract class DAOFactory {
    
    public static final int MYSQL = 1; 
    
    //Registro de los DAOS
    public abstract MessageDAO getMessageDAO();
    
    public static DAOFactory getDAOFactory(int whichFactory){
       switch(whichFactory){
       	case MYSQL:
       	    return new MySqlDAOFactory();  	    
       	default:
       	    return null;
       }
    }
}

