# Pizza Paradise

#### Team
- Jada Greene | Github: [@lynn0809](https://github.ccs.neu.edu/lynn0809) | Email: greene.jad@husky.neu.edu
- Yejee (Jenny) Lee | Github: [@yejeelee94](https://github.ccs.neu.edu/yejeelee94) | Email: lee.yej@husky.neu.edu
- Clara Mae Wells | Github: [@claramaewells](https://github.ccs.neu.edu/claramaewells) | Email: wells.cl@husky.neu.edu

This repository contains code for the Pizza Paradise API, an API that supports a pizza store. The API can be viewed live on the
[user interface](https://pizza-paradise.herokuapp.com/swagger-ui.html). 
Important infomation about the API is available in the 
[API design document](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadiseApiDesignDoc.pdf).

A front-end service was built using this API, and that Alexa skill may be viewed on
[LINK AFTER PUBLISHING]().
Information about the Alexa skill is available in the 
[front-end design document](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadiseFrontEndDesignDoc.pdf).

A summary of the entire project may be viewed on the 
[presentation video]()
([slides used in presentation](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadisePresentationSlides.pdf))
and in the
[final report](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadiseFinalProjectReport.pdf).
Finally, the project storyboard is accessible on 
[Trello](https://trello.com/b/lLhNXLeo/pizza-paradise).

#### Relevant documents
- [x] [Live API](https://pizza-paradise.herokuapp.com/swagger-ui.html)
- [ ] [API design document](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadiseApiDesignDoc.pdf)
- [x] [Front-end design document](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadiseFrontEndDesignDoc.pdf)
- [x] [Project storyboard](https://trello.com/b/lLhNXLeo/pizza-paradise)
- [ ] [Final report](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadiseFinalProjectReport.pdf)
- [ ] [Presentation video]()
- [x] [Presentation video slides](https://pizza-paradise.s3-us-west-2.amazonaws.com/resources/pizzaParadisePresentationSlides.pdf)

#### Setup instructions
- Create new Heroku app
- Add MongoDB
- Push to Heroku

#### Test instructions
- On CLI: mvn validate (to run automated tests)
- On CLI: mvn jacoco:report (to get detailed coverage reports)

#### Alexa skill instructions
- To test on Amazon Alexa app:
    1. Download Amazon Alexa app
    2. Login with Amazon account
    3. Invoke skill by saying "Alexa, order Pizza Paradise"
- To view source project
    1. Sign up for [Voiceflow](https://www.voiceflow.com/)
    2. View [copy of project development]()
    