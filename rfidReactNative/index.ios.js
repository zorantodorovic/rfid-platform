import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Navigator,
  TouchableOpacity
} from 'react-native';
import Login from './app/Login';
import Home from './app/Home';

export default class rfidReactNative extends Component {

    constructor(props) {
        super(props);
    }


    renderScene(route, navigator) {
        switch(route.name) {
            case 'login':
                return <Login navigator={navigator}/>
                break;
            case 'home':
                return <Home navigator={navigator}/>
                break;
            default:
                return <Login navigator={navigator}/>
        }
    }
    

    render() {
        return (
            <Navigator
                ref={'nav'}
                style={{flex: 1}}
                initialRoute={{ name: 'login' }}
                configureScene={(route) => ({...Navigator.SceneConfigs.HorizontalSwipeJump, gestures: true})}
                renderScene={ this.renderScene.bind(this) } />
        );
    }
}


AppRegistry.registerComponent('rfidReactNative', () => rfidReactNative);
