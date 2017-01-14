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
    ActivityIndicator,
    ListView
} from 'react-native';

class Listings extends Component {

	constructor(props) {
	  	super(props);
		const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    	this.state = {
      		dataSource: ds.cloneWithRows(this.props.data),
    	};
	}

	handleBackButton() {
		this.props.navigator.pop();
	}

	renderNavBar() {
        return (
            <View style={styles.toolbar}>
            	<TouchableHighlight
            		underlayColor={"transparent"}
            		style={styles.toolbarLeftButtonWrapper}
            		onPress={this.handleBackButton.bind(this)}>
            		<Text style={styles.toolbarLeftButton}>Back</Text>
            	</TouchableHighlight>
                <Text style={styles.toolbarTitle}>{this.props.title}</Text>
            </View>
        );
    }

    renderRow(data) {
    	return (
	          <View style={styles.row}>
	            <Text style={styles.text}>
	              {data}
	            </Text>
	          </View>
	    );
    }

  	render() {
	   	return (
	      	<View style={styles.mainWrapper}>
		      	{this.renderNavBar()}
		      	<ListView
		      	    dataSource={this.state.dataSource}
	      	        renderRow={this.renderRow.bind(this)}
	      	        style={styles.listView}
		      	/>
		    </View>
		);
  	}
}

const styles = StyleSheet.create({
	mainWrapper: {
		flex: 1,
		// alignItems: 'center'
	},
    toolbar: {
        backgroundColor:'#006CB2',
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        width: windowWidth,
        paddingBottom: 14,
        height: 64,
        flex: 1,
        flexDirection:'row',
        justifyContent: 'center',
        alignItems: 'center'
    },
    toolbarButtonWrapper: {
        width: 44, 
        height: 34,
        marginTop: 10,
    },
    toolbarLeftButton:{
        width: 50, 
        height: 22,
        marginLeft: 10,
        marginTop: 35,         
        backgroundColor: 'transparent',
        color: 'white'
    },
    toolbarTitle:{
    	flex: 1,
        color:'#fff',
        paddingBottom: 4,
        marginRight: 55,
        marginTop: 32,
        textAlign:'center',
        fontWeight: '600',
        fontSize: 17,   
    },
    listView: {
    	marginTop: 64,
    	width: windowWidth,
    	height: 500,
    	flex: 1,
    },
    row: {
        flexDirection: 'row',
        justifyContent: 'center',
        padding: 10,
        backgroundColor: '#F6F6F6',
    },
    text: {
    	flex: 1,
    	padding: 10,
    	color: "black"
    }
});


export default Listings;