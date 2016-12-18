local composer = require( "composer" )
local scene = composer.newScene()
local widget = require( "widget" )
local widgetExtras = require( "widget-extras" )
local json = require( "json" )

local titleText
local navBar
local emailLabel
local emailField
local passwordLabel
local lastNameField
local submitButton


-- -----------------------------------------------------------------------------------
-- Code outside of the scene event functions below will only be executed ONCE unless
-- the scene is removed entirely (not recycled) via "composer.removeScene()"
-- -----------------------------------------------------------------------------------
    
local function ignoreTouch( event )
    return true
end

local function fieldHandler( textField )
    return function( event )
        if ( "began" == event.phase ) then
            -- This is the "keyboard has appeared" event
            -- In some cases you may want to adjust the interface when the keyboard appears.
        
        elseif ( "ended" == event.phase ) then
            -- This event is called when the user stops editing a field: for example, when they touch a different field
            
        elseif ( "editing" == event.phase ) then
        
        elseif ( "submitted" == event.phase ) then
            -- This event occurs when the user presses the "return" key (if available) on the onscreen keyboard
            print( textField().text )
            
            -- Hide keyboard
            native.setKeyboardFocus( nil )
        end
    end
end    

local function submitForm( event )
    local record = {}
    record.email = emailField.text  
    record.password = passwordField.text
    -- print(passwordField.text)
    print( json.prettify( record ) )
    -- db.create( record )
    composer.hideOverlay()
end



-- -----------------------------------------------------------------------------------
-- Scene event functions
-- -----------------------------------------------------------------------------------

-- create()
function scene:create( event )

    local sceneGroup = self.view
    -- Code here runs when the scene is first created but has not yet appeared on screen
    -- local background = display.newImageRect( "background.png", 360, 570 )
    -- background.x = display.contentCenterX
    -- background.y = display.contentCenterY

    local background = display.newRect(0,0,display.contentWidth, display.contentHeight)
    background:setFillColor( 0.95, 0.95, 0.95 )
    -- background.x = display.contentWidth / 2
    -- background.y = display.contentHeight / 2

    background.x = display.contentCenterX
    background.y = display.contentCenterY

    sceneGroup:insert(background)

    navBar = widget.newNavigationBar({
        title = "RFID platform - Login",
        backgroundColor = { 0.96, 0.62, 0.34 },
        titleColor = {1, 1, 1},
        leftButton = leftButton
    })
    sceneGroup:insert(navBar)

    emailLabel = display.newText( "Email address", 10, navBar.y + navBar.height + 30, native.systemFont, 18 )
    emailLabel:setFillColor( 0.3, 0.3, 0.3 )
    emailLabel.anchorX = 0
    sceneGroup:insert( emailLabel )

    passwordLabel = display.newText( "Password", 10, emailLabel.y + 40, native.systemFont, 18 )
    passwordLabel:setFillColor( 0.3, 0.3, 0.3 )
    passwordLabel.anchorX = 0
    sceneGroup:insert( passwordLabel )

end


-- show()
function scene:show( event )

    local sceneGroup = self.view
    local phase = event.phase

    if ( phase == "will" ) then
        -- Code here runs when the scene is still off screen (but is about to come on screen)

    elseif ( phase == "did" ) then
        -- Code here runs when the scene is entirely on screen

        local fieldWidth = display.contentWidth - 150
        if fieldWidth > 250 then
            fieldWidth = 250
        end

        emailField = native.newTextField( 130, emailLabel.y, fieldWidth, 30 )
        emailField:addEventListener( "userInput", fieldHandler( function() return emailField end ) ) 
        sceneGroup:insert( emailField)
        emailField.anchorX = 0
        emailField.placeholder = "Email address"

        passwordField = native.newTextField( 130, passwordLabel.y, fieldWidth, 30 )
        passwordField:addEventListener( "userInput", fieldHandler( function() return passwordField end ) ) 
        passwordField.isSecure = true
        sceneGroup:insert( passwordField )
        passwordField.anchorX = 0
        passwordField.placeholder = "Password"

        submitButton = widget.newButton({
            width = 160,
            height = 40,
            label = "Submit",
            labelColor = { 
                default = { 0.90, 0.60, 0.34 }, 
                over = { 0.79, 0.48, 0.30 } 
            },
            labelYOffset = -4, 
            fontSize = 18,
            emboss = false,
            onRelease = submitForm
        })
        submitButton.x = display.contentCenterX
        submitButton.y = passwordField.y + 50
        sceneGroup:insert( submitButton )

    end
end


-- hide()
function scene:hide( event )

    local sceneGroup = self.view
    local phase = event.phase

    if event.phase == "will" then
        -- remove the addressField since it contains a native object.
        emailField:removeSelf()
        emailField = nil
        passwordField:removeSelf()
        passwordField = nil
    elseif ( phase == "did" ) then
        -- Code here runs immediately after the scene goes entirely off screen

    end
end


-- destroy()
function scene:destroy( event )

    local sceneGroup = self.view
    -- Code here runs prior to the removal of scene's view

end


-- -----------------------------------------------------------------------------------
-- Scene event function listeners
-- -----------------------------------------------------------------------------------
scene:addEventListener( "create", scene )
scene:addEventListener( "show", scene )
scene:addEventListener( "hide", scene )
scene:addEventListener( "destroy", scene )
-- -----------------------------------------------------------------------------------

return scene

