import {Link} from 'react-router';

const Header = React.createClass({
    getInitialState () {
        return {
            mainPhoneNumber: ""
        };
    },

    componentDidMount() {
        $.ajax({
            url: "/services/contacts",
            type: 'GET'
        }).
        done(this.updateContacts);
    },

    updateContacts(contacts) {
        return contacts && contacts.phoneNumbers && contacts.phoneNumbers.length &&
               this.setState({mainPhoneNumber: contacts.phoneNumbers[0]});
    },

    render() {
        return (
            <span className="header-top">
                <a href={"tel:" + this.state.mainPhoneNumber}>{this.state.mainPhoneNumber}</a>
                <Link to="/login">Log in</Link>
            </span>
        );
    }
});

export default Header;