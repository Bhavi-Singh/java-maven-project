package com.bhavi.jms.hr.hrapp;

import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.bhavi.jms.hr.Employee;

public class HRApp {

	public static void main(String[] args) throws NamingException {
		
		InitialContext context = new InitialContext();
		Topic empTopic = (Topic) context.lookup("topic/empTopic");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
                JMSContext jmsContext = cf.createContext()){
			
			Employee emp = new Employee();
			emp.setId(100);
			emp.setFirstName("Bhavi");
			emp.setLastName("Chauhan");
			emp.setDesignation("fULL Time");
			emp.setEmail("bhavi@gmail.com");
			emp.setPhone("9283629291");
			
			jmsContext.createProducer().send(empTopic,emp);
			System.out.println("Message Sent");
		}
	}

}
