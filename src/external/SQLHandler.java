package external;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import boat.Boat;
import server.IContext;
import users.Confidence;
import users.Subscriber;

/**
 * <b>SQLHandler is a helper managing the connection and queries to a MySQL
 * database</b>
 * <p>
 * A TCPClient instance is characterized by the following :
 * </p>
 * <ul>
 * <li>A SQL connection</li>
 * <li>A context to refer to</li>
 * </ul>
 * 
 * @author Mahmoud Souayfan and Georges Tanios
 */

public class SQLHandler {
	/**
	 * The connection to the database
	 */
	public Connection connection = null;
	/**
	 * The context providing a link to the central system
	 */
	public IContext context;

	/**
	 * SQLHandler Constructor.
	 * <p>
	 * Through construction, the SQLHandler simply gathers the context
	 * </p>
	 * 
	 * @param aContext The Central System data
	 * 
	 * @see SQLHandler
	 * @see SQLHandler#context
	 */

	public SQLHandler(IContext aContext) {
		this.context = aContext;
	}

	/**
	 * Updates the database if a new subscriber is added to the central system
	 * 
	 * @param subscriberList the list of subscriber to update in the database
	 * @see SQLHandler#context
	 */

	public void updateDataBase(List<Subscriber> subscriberList) {
		// TODO : update the subscriber or add new subscriber if the id of the
		// subscriber not found in the database
		String Query;

		for (Subscriber s : subscriberList) {
			try {
				PreparedStatement pst;

				// Insert subsriber database into table : "subscriber"
				Query = "INSERT INTO subscriber(subscriber_account, subscriber_password, subscriber_name, subscriber_mail, subscriber_address, subscriber_date)"
						+ "VALUES ('" + s.getAccount() + "','" + s.getPassword() + "','" + s.getName() + "','"
						+ s.getEmail() + "','" + s.getAddress() + "','" + s.getSubscriptionDate() + "')";

				pst = connection.prepareStatement(Query);

				pst.executeQuery();
				pst.close();

				// Insert Boat database into table : "Boat"
				Query = "INSERT INTO Boat (boat_numero_im, boat_name, boat_type, boat_model)" + "VALUES ('"
						+ s.getBoat().getImmatriculation() + "','" + s.getBoat().getName() + "','"
						+ s.getBoat().getType() + "','" + s.getBoat().getModel() + "')";

				pst = connection.prepareStatement(Query);
				pst.executeQuery();
				pst.close();

				// Insert Confidence database table : "Confidence"
				for (Confidence c : s.getTrustedPersons()) {
					Query = "INSERT INTO Confidence (Number, Email)" + "VALUES ('" + c.getNumber() + "','"
							+ c.getEmail() + "')";

				}

				pst = connection.prepareStatement(Query);
				pst.executeQuery();
				pst.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		// String Query = "Select "

	}

	// in this function we extract all information about subscriber and boat and
	// confidence and put the informations in
	// the list.

//	public void updateSubscriber(int subscriberID)
//	{
//		
//	}

	/**
	 * Extract all the database to initialize the central system
	 * 
	 * @see SQLHandler#context
	 */

	public void extractData() {
		// TODO : extract all data of subscriber and boat and confidence and put it in
		// the list
		try {
			ResultSet Resultat;

			String query = "SELECT * FROM subscriber ";

			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet RS = pst.executeQuery();

			int count = 0;
			Subscriber subscriber;
			Boat boat;
			Confidence confidence;
			// varibale de bateau
			String immatriculation = "";
			String boat_name = "";
			String boat_type = "";
			String model = "";
			String position = "";
			String place = "";
			String state = "";
			// variable confidence
			String email_conf = "";
			String number_conf = "";

			// variable pour le subscriber
			int boatID;
			int confidenceID;
			String subscriberType;
			String account;
			String password;
			String subscriber_name;
			String address;
			String subscriber_email;
			LocalDate subscription_date;
			// Confidence trusted_persons;

			while (RS.next()) {
				count += 1;

				// *********************************************************
				boatID = Integer.parseInt(RS.getString("subscriber_boat_id"));
				confidenceID = Integer.parseInt(RS.getString("confidence_id"));
				subscriberType = RS.getString("subscriber_type");
				account = RS.getString("subscriber_account");
				password = RS.getString("subscriber_password");
				subscriber_name = RS.getString("subscriber_name");
				address = RS.getString("subscriber_address");
				subscriber_email = RS.getString("subscriber_mail");
				subscription_date = LocalDate.parse(RS.getString("subscriber_date"));
				// trusted_persons = RS.getString("confidence_id");
				// subscriber = new Subscriber(subscriberID,account, password, subscriber_name,
				// address, subscriber_email, subscription_date, subscriberType, boatID,
				// confidenceID);
				if (subscriberType == "user") {

					// ********************** Boat **********************************
					query = "SELECT * FROM Boat WHERE  boat_id =" + boatID;
					pst = connection.prepareStatement(query);
					Resultat = pst.executeQuery();
					immatriculation = Resultat.getString("boat_numero_im");
					boat_name = Resultat.getString("boat_name");
					boat_type = Resultat.getString("boat_type");
					model = Resultat.getString("boat_model");
					position = Resultat.getString("boat_position");
					place = Resultat.getString("boat_place");
					state = Resultat.getString("boat_state");
					boat = new Boat(immatriculation, boat_name, boat_type, model, position, place, state);
					context.addBoat(boat);

					// ************************** Confidence ***************************
					query = "SELECT * FROM Confidence WHERE Confidence_id =" + confidenceID;
					pst = connection.prepareStatement(query);
					Resultat = pst.executeQuery();
					email_conf = Resultat.getString("Email");
					number_conf = Resultat.getString("Number");
					confidence = new Confidence(email_conf, number_conf);
					context.addConfidence(confidence);

					Resultat.close();

				}

				subscriber = new Subscriber(account, password, subscriber_name, address, subscriber_email,
						subscription_date, email_conf, number_conf, immatriculation, boat_name, boat_type, model);

				context.addSubscriber(subscriber);

			}

			// si le count est "0" alors il n y a aucun subscriber dans la base de donnee
			if (count == 0) {
				System.out.println("NO SUBSCRIBERS!");
			}

			RS.close();
			pst.close();
			connection.close();
		} catch (Exception exp) {
			JOptionPane.showMessageDialog(null, exp);
		}
	}
}
