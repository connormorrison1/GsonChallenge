# Programming Exercise

I utilized a third-party library that was suggested within the prompt, Gson. I had never used this library before, but its ability to translate a JSON string to its equivalent Java object seemed incredibly useful for comparison.

## File Structure

I wanted some way to organize the data, so I opted for two packages: one that holds the Java objects relating to the parsed JSON data (Brewery, Beer, Location, etc.), and one that holds the JSON data, and comparisons of the data itself.

## Gson

Using Gson, a third-party library from Google, I was able to parse the JSON and use that to create java objects for each of properties found within the files. Within each of these files, I set each of the attributes to be private, and used getters to grab each of these values. This is to add simple encapsulation to the program.

## Determining Equality

The breakdown of how I determined the score was like this:
- If the state birds don't match, then subtract 0.1 from the score.
- If the states don't match, subtract 0.2 from the score.
- If the state fishes don't match, subtract 0.1 from the score.

**Next is breweries, which account for 0.6 of the total score**

- If the same Breweries names are not found, subtract 1.0/(size of the list of Breweries).
- If the food available doesn't match, subtract 1.0/(size of the list of Breweries).
- If the location doesn't match, subtract (1.0/(size of the list of Breweries)) * equality score for location (maximum 1.0).
- If the beers do not match, subtract (1.0/(size of the list of Breweries)) * equality score for the beer (maximum 1.0).
**Finally, return the score (up to 1.0).**

If everything is equal, then the score will be equal to 1.0.
