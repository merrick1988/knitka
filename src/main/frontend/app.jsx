import 'babel/polyfill';
import Routes from 'routes';

import './styles/styles.scss';

window.console = window.console || {error() {}, log() {}, debug() {}};

ReactDOM.render((
    <div>
        <Routes/>
    </div>
), document.getElementById('content'));

_.noConflict();