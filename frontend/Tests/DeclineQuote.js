const { By, Builder, until } = require('selenium-webdriver');
require('chromedriver');
const assert = require('assert');

async function test_case() {
    let driver = await new Builder().forBrowser('chrome').build();

    try {
        await driver.get('http://localhost:3000/');

        // Wait for the Reviewer button to be clickable
        await driver.wait(until.elementLocated(By.id('reviewerBtn')), 10000); // increased to 10 seconds
        const reviewerLink = await driver.findElement(By.id('reviewerBtn'));
        await reviewerLink.click();

        await driver.sleep(5000); // added a 5-second wait

        const quoteId = 'a1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p7';

        await driver.wait(until.elementLocated(By.id(`btn-${quoteId}`)), 10000); // increased to 10 seconds
        const viewButton = await driver.findElement(By.id(`btn-${quoteId}`));
        await viewButton.click();

        await driver.sleep(5000); // added a 5-second wait

        await driver.wait(until.elementLocated(By.id('DetailQuoteId')), 10000); // increased to 10 seconds

        const declineButton = await driver.findElement(By.id('declineBtn'));
        await declineButton.click();

        await driver.sleep(5000); // added a 5-second wait

        // Wait for success toast message or another indicator of successful decline
        await driver.wait(until.elementLocated(By.className('Toastify__toast--success')), 10000); // increased to 10 seconds

        await viewButton.click();
        await driver.wait(until.elementLocated(By.id('DetailQuoteId')), 10000); // increased to 10 seconds

        await driver.sleep(10000); // Wait for visual confirmation

        // Add assertions here as needed

    } finally {
        await driver.quit();
    }
}

test_case();
