const { By, Builder } = require('selenium-webdriver')
require('chromedriver')
const assert = require('assert')

async function test_case() {
    let driver = await new Builder().forBrowser('chrome').build()

    await driver.get('http://localhost:3000/Reviewer')

    // Wait for 5 seconds
    await new Promise((resolve) => setTimeout(resolve, 2000))

    assert.strictEqual(
        await driver.getCurrentUrl(),
        'http://localhost:3000/Reviewer'
    )

    const view = await driver.findElements(By.tagName('button'))[0]
    view.click()

    const actualQuoteId = 'a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6';
    const actualQuoteStatus = 'PENDING';
    const actualPickupStreetAddress = '123 Main St';
    const actualPickupCity = 'Cityville';
    const actualPickupCountry = 'CA';
    const actualPickupPostalCode = 'M1A 1A1';
    const actualPickupNumberOfRooms = 3;
    const actualPickupElevator = true;
    const actualPickupBuildingType = 'Apartment';
    const actualDestinationStreetAddress = '456 Oak St';
    const actualDestinationCity = 'Townsville';
    const actualDestinationCountry = 'USA';
    const actualDestinationPostalCode = 'M5V 2H1';
    const actualDestinationNumberOfRooms = 4;
    const actualDestinationElevator = false;
    const actualDestinationBuildingType = 'House';
    const actualFirstName = 'John';
    const actualLastName = 'Doe';
    const actualEmailAddress = 'john.doe@example.com';
    const actualPhoneNumber = '123-555-1234';
    const actualExpectedMovingDate = '2023-12-15';
    const actualInitiationDate = '2023-12-13 08:30:00';
    const actualComment = 'Moving details for John Doe';
    const actualShipmentName = 'ShipmentXYZ';

    const quoteId = await driver.findElement(By.id('ShipmentId'))
    assert.strictEqual(await quoteId.getText(), actualQuoteId, 'quoteId assertion failed')

    const quoteStatus = await driver.findElement(By.id('QuoteStatus'));
    assert.strictEqual(await quoteStatus.getText(), actualQuoteStatus, 'quoteStatus assertion failed');

    const pickupStreetAddress = await driver.findElement(By.id('PickupStreeAddress'));
    assert.strictEqual(await pickupStreetAddress.getText(), actualPickupStreetAddress, 'pickupStreetAddress assertion failed');

    const pickupCity = await driver.findElement(By.id('PickupCity'));
    assert.strictEqual(await pickupCity.getText(), actualPickupCity, 'pickupCity assertion failed');

    const pickupCountry = await driver.findElement(By.id('PickupCountry'));
    assert.strictEqual(await pickupCountry.getText(), actualPickupCountry, 'pickupCountry assertion failed');

    const pickupPostalCode = await driver.findElement(By.id('PickupPostalCode'));
    assert.strictEqual(await pickupPostalCode.getText(), actualPickupPostalCode, 'pickupPostalCode assertion failed');

    const pickupNumberOfRooms = await driver.findElement(By.id('PickupNumberOfRooms'));
    assert.strictEqual(await pickupNumberOfRooms.getText(), actualPickupNumberOfRooms.toString(), 'pickupNumberOfRooms assertion failed');

    const pickupElevator = await driver.findElement(By.id('PickupYes'));
    assert.strictEqual(await pickupElevator.isSelected(), actualPickupElevator, 'pickupElevator assertion failed');

    const pickupBuildingType = await driver.findElement(By.id('PickupBuildingType'));
    assert.strictEqual(await pickupBuildingType.getText(), actualPickupBuildingType, 'pickupBuildingType assertion failed');

    const destinationStreetAddress = await driver.findElement(By.id('DestinationAddress'));
    assert.strictEqual(await destinationStreetAddress.getText(), actualDestinationStreetAddress, 'destinationStreetAddress assertion failed');

    const destinationCity = await driver.findElement(By.id('DestinationCity'));
    assert.strictEqual(await destinationCity.getText(), actualDestinationCity, 'destinationCity assertion failed');

    const destinationCountry = await driver.findElement(By.id('DestinationCountry'));
    assert.strictEqual(await destinationCountry.getText(), actualDestinationCountry, 'destinationCountry assertion failed');

    const destinationPostalCode = await driver.findElement(By.id('DestinationPostalCode'));
    assert.strictEqual(await destinationPostalCode.getText(), actualDestinationPostalCode, 'destinationPostalCode assertion failed');

    const destinationNumberOfRooms = await driver.findElement(By.id('DestinationNumberOfRooms'));
    assert.strictEqual(await destinationNumberOfRooms.getText(), actualDestinationNumberOfRooms.toString(), 'destinationNumberOfRooms assertion failed');

    const destinationElevator = await driver.findElement(By.id('PickupNo'));
    assert.strictEqual(await destinationElevator.isSelected(), actualDestinationElevator, 'destinationElevator assertion failed');

    const destinationBuildingType = await driver.findElement(By.id('DestinationBuildingType'));
    assert.strictEqual(await destinationBuildingType.getText(), actualDestinationBuildingType, 'destinationBuildingType assertion failed');

    const firstName = await driver.findElement(By.id('FirstName'));
    assert.strictEqual(await firstName.getText(), actualFirstName, 'firstName assertion failed');

    const lastName = await driver.findElement(By.id('LastName'));
    assert.strictEqual(await lastName.getText(), actualLastName, 'lastName assertion failed');

    const emailAddress = await driver.findElement(By.id('Email'));
    assert.strictEqual(await emailAddress.getText(), actualEmailAddress, 'emailAddress assertion failed');

    const phoneNumber = await driver.findElement(By.id('PhoneNumber'));
    assert.strictEqual(await phoneNumber.getText(), actualPhoneNumber, 'phoneNumber assertion failed');

    const contactMethod = await driver.findElement(By.id('PreferredEmail'));
    assert.strictEqual(await contactMethod.isSelected(), true, 'contactMethod assertion failed');

    const expectedMovingDate = await driver.findElement(By.id('ExpectedMovingDate'));
    assert.strictEqual(await expectedMovingDate.getText(), actualExpectedMovingDate, 'expectedMovingDate assertion failed');

    const initiationDate = await driver.findElement(By.id('InitiationDate'));
    assert.strictEqual(await initiationDate.getText(), actualInitiationDate, 'initiationDate assertion failed');

    const comment = await driver.findElement(By.id('Comment'));
    assert.strictEqual(await comment.getText(), actualComment, 'comment assertion failed');

    const shipmentName = await driver.findElement(By.id('ShipmentName'));
    assert.strictEqual(await shipmentName.getText(), actualShipmentName, 'shipmentName assertion failed');

    await new Promise((resolve) => setTimeout(resolve, 5000))

    const close = await driver.findElement(By.id('CloseDetail'));
    close.click()

    // Close the browser after waiting for another 5 seconds
    await new Promise((resolve) => setTimeout(resolve, 1000))
    await driver.quit()
}
test_case()
