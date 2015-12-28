import 'babel/polyfill';
import Routes from 'routes';
import Header from 'components/header/header';

import './styles/styles.scss';

window.console = window.console || {error() {}, log() {}, debug() {}};

ReactDOM.render((
    <div>
        <Header/>
        <Routes/>
    </div>
), document.getElementById('content'));

_.noConflict();