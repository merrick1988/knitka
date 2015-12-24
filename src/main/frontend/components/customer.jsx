import $ from "jquery";

const Customer = React.createClass({
    getInitialState () {
        return {
            customer: {}
        };
    },

    getCustomers() {
        $.ajax({
            url: "/services/customers/1",
            type: 'GET',
            dataType: "xml",
            contentType: "application/xml"
        }).
        done(response => {
            const xmlDoc = $(response);

            this.setState({
                customer: {
                    firstName: xmlDoc.find("first-name").text() || "",
                    lastName: xmlDoc.find("last-name").text() || ""
                }
            });
        });
    },

    createCustomer() {
        $.ajax({
            url: "/services/customers",
            type: 'POST',
            dataType: "xml",
            contentType: "application/xml",
            data: `
                <customer id="117">
                   <first-name>Bill</first-name>
                   <last-name>Burke</last-name>
                </customer>
            `
        });
    },

    render () {
        return (
            <div>
                <button onClick={this.createCustomer}>Create Customer</button>
                <button onClick={this.getCustomers}>Get Customer</button>
                <span>{this.state.customer.firstName} {this.state.customer.lastName}</span>
            </div>
        );
    }
});

export default Customer;