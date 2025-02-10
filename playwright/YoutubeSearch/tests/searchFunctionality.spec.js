const {test, expect } =  require('@playwright/test');
const { assert } = require('console');

test('Search Functionality', async({page})=> {
    //open youtube
    await page.goto("https://www.youtube.com/");
    //Enter a search query in the search bar.
    await page.getByPlaceholder("Search").first().fill("swapnavenuvedo song");
    await page.locator(".ytSearchboxComponentSearchButton").click();
    //await page.pause();
    //wait until page load
    await page.locator("ytd-video-renderer.ytd-item-section-renderer").first().waitFor();

    // Verify that the search results are displayed.
    //get all songs list items
    const songsList = (await page.locator("a#video-title").allTextContents()).map(title => title.trim());
    console.log(songsList);
  
    const songTitle = await page.locator("a#video-title").first().textContent();
    console.log("Selected Song Title:", songTitle);

    await page.locator("a#video-title").filter({hasText: 'Swapnavenuvedo Video Song ||  Ravoyi Chandamama Movie'}).first().click();
    
    
    const openedTitleSong = await page.locator("h3.title-and-badge.style-scope.ytd-video-renderer").first();

    // Await the actual text content before comparing
    const openedTitleText = await openedTitleSong.textContent();
    console.log("Opened Video Title:", openedTitleText);
    
    await expect(openedTitleSong).toHaveText(songTitle);
  
// Click on a video and verify that it starts playing.


})