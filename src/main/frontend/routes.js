import {Router, Route} from 'react-router';
import {createHashHistory} from 'history/lib';
import Main from 'pages/main';
import Login from 'pages/login';

class Routes extends React.Component {
    render() {
        return (
            <Router history={createHashHistory({queryKey: false})}>
                <Route path="/" component={Main}>
                    <Route path="login" component={Login}/>
                </Route>
            </Router>
        );
    }
}

export default Routes;
