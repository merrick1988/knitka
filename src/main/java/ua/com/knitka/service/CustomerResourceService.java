package ua.com.knitka.service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ua.com.knitka.model.CustomerDTO;
import ua.com.knitka.resource.CustomerResource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerResourceService implements CustomerResource {
    private Map<Integer, CustomerDTO> customerDB =
            new ConcurrentHashMap<Integer, CustomerDTO>();

    private AtomicInteger idCounter = new AtomicInteger();

    protected void outputCustomer(OutputStream os, CustomerDTO cust)
            throws IOException {
        PrintStream writer = new PrintStream(os);
        writer.println("<customer id=\"" + cust.getId() + "\">");
        writer.println("   <first-name>" + cust.getFirstName()
                + "</first-name>");
        writer.println("   <last-name>" + cust.getLastName()
                + "</last-name>");
        writer.println("</customer>");
    }

    protected CustomerDTO readCustomer(InputStream is) {
        try {
            DocumentBuilder builder =
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(is);
            Element root = doc.getDocumentElement();
            CustomerDTO customer = new CustomerDTO();

            if (root.getAttribute("id") != null
                    && !root.getAttribute("id").trim().equals("")) {
                customer.setId(Integer.valueOf(root.getAttribute("id")));
            }

            NodeList nodes = root.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node none = nodes.item(i);

                if (none.getNodeName().equals("first-name")) {
                    customer.setFirstName(none.getTextContent());
                }
                else if (none.getNodeName().equals("last-name")) {
                    customer.setLastName(none.getTextContent());
                }
            }

            return customer;
        }
        catch (Exception e) {
            throw new WebApplicationException(e,
                    Response.Status.BAD_REQUEST);
        }
    }

    public Response createCustomer(InputStream is) {
        CustomerDTO customer = readCustomer(is);
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);

        return Response.created(URI.create("/customers/"
                + customer.getId())).build();
    }

    public StreamingOutput getCustomer(int id) {
        final CustomerDTO customer = customerDB.get(id);

        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new StreamingOutput() {
            public void write(OutputStream outputStream)
                    throws IOException, WebApplicationException {
                outputCustomer(outputStream, customer);
            }
        };
    }

    public void updateCustomer(int id, InputStream update) {
        CustomerDTO current = customerDB.get(id);
        if (current == null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);

//        current.setFirstName(update.getFirstName());
//        current.setLastName(update.getLastName());
    }
}