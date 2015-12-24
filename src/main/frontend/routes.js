import {Router, Route} from 'react-router';
import Main from 'pages/main';

class Routes extends React.Component {
    render() {
        return (
            <Router>
                <Route path="/" component={Main} />
            </Router>
        );
    }
}

export default Routes;
