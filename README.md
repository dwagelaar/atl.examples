# atl.examples
Eclipse ATL/EMFTVM example transformations

## Installation

This repository contains several Eclipse ATL example projects that can be imported into your Eclipse workspace. It is assumed you've got your [Eclipse IDE](https://www.eclipse.org/downloads/) installed together with the [ATL](https://www.eclipse.org/atl/) plug-ins. Just clone this repository, and import the whole repository as Eclipse projects like this:

  1. Click the green "Code / Clone" button above, and click the clipboard icon next to the repo URI to copy it to the clipboard
  2. Open your Eclipse IDE
  3. Select "File &rarr; Import..." from the main menu
  4. Select "Git &rarr; Projects from Git" and click "Next"
  5. Select "Clone URI" and click "Next"
  6. The correct git repo URI has already been filled in from the clipboard; just click "Next"
  7. Finish the remainder of wizard with the default options

## Running the examples

Each of the example Eclipse ATL projects comes with one or more [Eclipse launch configurations](https://eclipsesnippets.blogspot.com/2007/07/tip-creating-and-sharing-launch.html), which are stored in the `launch` folder inside each project. Eclipse automatically recognises the launch configurations, and makes them available in the "Run -> Run Configurations..." menu:

  1. Select "Run &rarr; Run Configurations..." from the main menu
  2. Find your transformation under "ATL EMFTVM Transformation" and select it
  3. Click "Run"
