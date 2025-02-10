// @ts-check
const { defineConfig, devices } = require('@playwright/test');
const { on } = require('events');


module.exports = defineConfig({
  testDir: './tests',
  /* Run tests in files in parallel */
  //fullyParallel: true,

  timeout: 30* 1000,

  expect: {
    timeout: 5000
  },
  
  reporter: 'html',
  /* Shared settings for all the projects below. See https://playwright.dev/docs/api/class-testoptions. */
  use: {

    browserName: 'chromium',

    headless: false,
    trace: 'on',
    screenshot: 'on',

 
  },

 
});

