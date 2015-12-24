const webpack = require("webpack"),
    path = require("path"),
    appPath = __dirname,
    frontendPath = path.join(__dirname, "src/main/frontend"),
    distPath = path.join(__dirname, "/src/main/webapp/knitka");

module.exports = {
    cache: true,
    entry: {
        main: [
            path.join(frontendPath, 'app.jsx')
        ]
    },
    resolve: {
        root: frontendPath,
        extensions: ['', '.jsx', '.js']
    },
    output: {
        path: distPath,
        publicPath: "/knitka",
        filename: '[name].js',
        libraryTarget: 'this',
        library: '[name]'
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                loader: [
                    'react-hot',
                    'babel?optional[]=es7.classProperties&optional[]=es7.objectRestSpread&optional[]=es7.decorators',
                    'autoimport?config[]=checkIfUsed&' + [
                        'React=>react',
                        'ReactDOM=react-dom',
                        '_=lodash',
                        'classnames'
                    ].join(','),
                    'eslint'
                ].join('!')
            },

            {
                test: /\.scss$/,
                loaders: ['style', 'css', 'sass']
            },
            {
                test: /\.(png|jpg|svg)$/,
                loader: 'url-loader?limit=8192&name=[name].[ext]'
            },
            {
                test: /fonts\/.*\.(eot|ttf|woff|svg|svgz)$/,
                loader: 'file?name=fonts/[name].[ext]'
            }
        ]
    },
    devServer: {
        host: 'localhost',
        port: '1234',
        quiet: false,
        noInfo: false,
        proxy: {
            '*': 'http://localhost:8080/'
        },
        stats: {
            chunks: false,
            chunkModules: false,
            timings: true
        }

    }
};