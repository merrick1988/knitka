import Header from 'components/header/header';

class Main extends React.Component {
    render () {
        return (
            <div>
                <Header/>
                <main>
                    {this.props.children}
                </main>
            </div>
        );
    }
}

export default Main;