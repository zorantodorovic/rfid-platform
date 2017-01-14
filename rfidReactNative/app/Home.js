'use strict';

import React, { Component } from 'react';
import Dimensions from 'Dimensions';

var windowWidth = Dimensions.get('window').width;

import {
	StyleSheet,
    View,
    Text,
    TextInput,
    TouchableHighlight,
    ActivityIndicator
} from 'react-native';

class Home extends Component {

	constructor(props) {
	  super(props);
	
	  this.state = {};
	}

	renderNavBar() {
        return (
            <View style={styles.toolbar}>
                <Text style={styles.toolbarTitle}>Home</Text>
            </View>
        );
    }

  	render() {
	   	return (
	      	<View style={styles.mainWrapper}>
		      	{this.renderNavBar()}
		      	<View style={styles.formWrapper}>
		        	<TextInput 
		        		secureTextEntry={true}
			      		style={{width: windowWidth, height: 40, borderColor: 'gray', borderWidth: 1, color: 'gray', marginTop: 15,paddingLeft: 15}}
		        		onChangeText={(text) => this.setState({password: text})}
	                    placeholder={"Password"}
		        		value={this.state.password}
		        	/>
		      	</View>
		    </View>
		);
  	}
}

const styles = StyleSheet.create({
	mainWrapper: {
		flex: 1,
		alignItems: 'center'
	},
    toolbar: {
        backgroundColor:'#006CB2',
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        paddingBottom: 14,
        height: 64,
        flex: 1,
        flexDirection:'row',
        justifyContent: 'center',
        alignItems: 'center'
    },
    toolbarButtonWrapper: {
        flex: 1,
        width: 44, 
        height: 34,
        paddingRight: 20,
        justifyContent: 'flex-start',
    },
    toolbarLeftButton:{
        width: 22, 
        height: 22,
        marginLeft: 15,
        marginTop: 14,         
        flex: 1,
        backgroundColor: 'transparent',
        resizeMode: 'contain'
    },
    toolbarButton:{
        width: 22, 
        height: 22,      
        flex: 1,
        backgroundColor: 'transparent'
    },
    toolbarTitle:{
        color:'#fff',
        position: 'absolute',
        paddingBottom: 4,
        top: 32,
        left: 50,
        right: 50,
        textAlign:'center',
        fontWeight: '600',
        fontSize: 17,   
    },
    formWrapper: {
    	marginTop: 100,
    	flex: 1,
    	alignItems: 'center'
    },
    button: {
    	backgroundColor: 'gray',
    	width: 100,
    	height: 50,
    	alignItems: 'center',
    	justifyContent: 'center',
    	borderRadius: 2,
    	marginTop: 15
    }
});


export default Home;