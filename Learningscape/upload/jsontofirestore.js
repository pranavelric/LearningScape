
var fs = require("fs")
const output = fs.createWriteStream('./stdout.log');
const errorOutput = fs.createWriteStream('./stderr.log');

const { keys } = Object;
const console = require('console');


const firebase = require("firebase");
// Required for side-effects
require("firebase/firestore");

var admin = require("firebase-admin");

var serviceAccount = require('D:/Learningscape/upload/key.json');
const { Console } = require("console");

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://fir-uipractice-fc87a.firebaseio.com"
});
// Initialize Cloud Firestore through Firebase
firebase.initializeApp({
    apiKey: "your key",
    authDomain: "",
    databaseURL: "",
    projectId: "",
    storageBucket: "",
    messagingSenderId: "",
    appId: "",
    measurementId: ""
});

var db = firebase.firestore();

var Categories =
{
    "Cat15": "Animals",


};
var QuestionsNum = [
    "Question1",
    "Question2",
    "Question3",
    "Question4",
    "Question5",
    "Question6",
    "Question7",
    "Question8",
    "Question9",
    "Question10",
    "Question11",
    "Question12",
    "Question13",
    "Question14",
    "Question15",
    "Question16",

    "Question17",
    "Question18",
    "Question19",
    "Question21",
    "Question22",
    "Question23",
    "Question24",
    "Question25",
    "Question26",
    "Question27",
    "Question28",
    "Question29",
    "Question30",
    "Question31",
    "Question32",
    "Question33",
    "Question34",
    "Question35",
    "Question36",
    "Question37",
    "Question38",
    "Question39",
    "Question40",
    "Question41",
    "Question42",
    "Question43",
    "Question44",
    "Question45",
    "Question46",
    "Question47",
    "Question48",
    "Question49",
    "Question50",
    "Question51",


];

var prop = [

    {
        "Question": "What was the name of the Ethiopian Wolf before they knew it was related to wolves?",
        "Answer": "Simien Jackel",
        "A": "Ethiopian Coyote",
        "B": "Amharic Fox",
        "C": "Canis Simiensis",
        "D": "Simien Jackel"
    },
    {
        "Question": "What does 'hippopotamus' mean and in what langauge?",
        "Answer": "River Horse (Greek)",
        "A": "River Horse (Latin)",
        "B": "Fat Pig (Greek)",
        "C": "Fat Pig (Latin)",
        "D": "River Horse (Greek)"
    },
    {
        "Question": "A carnivorous animal eats flesh_ what does a nucivorous animal eat?",
        "Answer": "Nuts",
        "A": "Nothing",
        "B": "Fruit",
        "C": "Seaweed",
        "D": "Nuts"
    },
    {
        "Question": "What type of animal is a natterjack?",
        "Answer": "Toad",
        "A": "Bird",
        "B": "Fish",
        "C": "Insect",
        "D": "Toad"
    },
    {
        "Question": "What is the fastest land animal?",
        "Answer": "Cheetah",
        "A": "Lion",
        "B": "Thomson Gazelle",
        "C": "Pronghorn Antelope",
        "D": "Cheetah"
    },
    {
        "Question": "What is the scientific name for modern day humans?",
        "Answer": "Homo Sapiens",
        "A": "Homo Ergaster",
        "B": "Homo Erectus",
        "C": "Homo Neanderthalensis",
        "D": "Homo Sapiens"
    },
    {
        "Question": "The Kākāpō is a large_ flightless_ nocturnal parrot native to which country?",
        "Answer": "New Zealand",
        "A": "South Africa",
        "B": "Australia",
        "C": "Madagascar",
        "D": "New Zealand"
    },
    {
        "Question": "Which species of Brown Bear is not extinct?",
        "Answer": "Syrian Brown Bear",
        "A": "California Grizzly Bear",
        "B": "Atlas Bear",
        "C": "Mexican Grizzly Bear",
        "D": "Syrian Brown Bear"
    },
    {
        "Question": "What color/colour is a polar bear's skin?",
        "Answer": "Black",
        "A": "White",
        "B": "Pink",
        "C": "Green",
        "D": "Black"
    },
    {
        "Question": "Hippocampus is the Latin name for which marine creature?",
        "Answer": "Seahorse",
        "A": "Dolphin",
        "B": "Whale",
        "C": "Octopus",
        "D": "Seahorse"
    },
    {
        "Question": "Cashmere is the wool from which kind of animal?",
        "Answer": "Goat",
        "A": "Sheep",
        "B": "Camel",
        "C": "Llama",
        "D": "Goat"
    },
    {
        "Question": "What is the scientific name for the 'Polar Bear'?",
        "Answer": "Ursus Maritimus",
        "A": "Polar Bear",
        "B": "Ursus Spelaeus",
        "C": "Ursus Arctos",
        "D": "Ursus Maritimus"
    },
    {
        "Question": "What are rhino's horn made of?",
        "Answer": "Keratin",
        "A": "Bone",
        "B": "Ivory",
        "C": "Skin",
        "D": "Keratin"
    },
    {
        "Question": "How many legs do butterflies have?",
        "Answer": "6",
        "A": "2",
        "B": "4",
        "C": "0",
        "D": "6"
    },
    {
        "Question": "What scientific suborder does the family Hyaenidae belong to?",
        "Answer": "Feliformia",
        "A": "Haplorhini",
        "B": "Caniformia",
        "C": "Ciconiiformes",
        "D": "Feliformia"
    },
    {
        "Question": "How many known living species of hyenas are there?",
        "Answer": "4",
        "A": "8",
        "B": "2",
        "C": "6",
        "D": "4"
    },
    {
        "Question": "What scientific family does the Aardwolf belong to?",
        "Answer": "Hyaenidae",
        "A": "Canidae",
        "B": "Felidae",
        "C": "Eupleridae",
        "D": "Hyaenidae"
    },
    {
        "Question": "For what reason would a spotted hyena 'laugh'?",
        "Answer": "Nervousness",
        "A": "Excitement",
        "B": "Aggression",
        "C": "Exhaustion",
        "D": "Nervousness"
    },
    {
        "Question": "What do you call a baby bat?",
        "Answer": "Pup",
        "A": "Cub",
        "B": "Chick",
        "C": "Kid",
        "D": "Pup"
    },
    {
        "Question": "What is the scientific name of the cheetah?",
        "Answer": "Acinonyx jubatus",
        "A": "Panthera onca",
        "B": "Lynx rufus",
        "C": "Felis catus",
        "D": "Acinonyx jubatus"
    },
    {
        "Question": "Which animal was part of an Russian domestication experiment in 1959?",
        "Answer": "Foxes",
        "A": "Pigeons",
        "B": "Bears",
        "C": "Alligators",
        "D": "Foxes"
    },
    {
        "Question": "The now extinct species 'Thylacine' was native to where?",
        "Answer": "Tasmania_ Australia",
        "A": "Baluchistan_ Pakistan",
        "B": "Wallachia_ Romania",
        "C": "Oregon_ United States",
        "D": "Tasmania_ Australia"
    },
    {
        "Question": "What is the Gray Wolf's scientific name?",
        "Answer": "Canis Lupus",
        "A": "Canis Aureus",
        "B": "Canis Latrans",
        "C": "Canis Lupus Lycaon",
        "D": "Canis Lupus"
    },
    {
        "Question": "Which of these animals is NOT a lizard?",
        "Answer": "Tuatara",
        "A": "Komodo Dragon",
        "B": "Gila Monster",
        "C": "Green Iguana",
        "D": "Tuatara"
    },
    {
        "Question": "What is Grumpy Cat's real name?",
        "Answer": "Tardar Sauce",
        "A": "Sauce",
        "B": "Minnie",
        "C": "Broccoli",
        "D": "Tardar Sauce"
    },
    {
        "Question": "What type of creature is a Bonobo?",
        "Answer": "Ape",
        "A": "Lion",
        "B": "Parrot",
        "C": "Wildcat",
        "D": "Ape"
    },
    {
        "Question": "Which of these species is not extinct?",
        "Answer": "Komodo dragon",
        "A": "Japanese sea lion",
        "B": "Tasmanian tiger",
        "C": "Saudi gazelle",
        "D": "Komodo dragon"
    },
    {
        "Question": "Which class of animals are newts members of?",
        "Answer": "Amphibian",
        "A": "Fish",
        "B": "Reptiles",
        "C": "Mammals",
        "D": "Amphibian"
    },
    {
        "Question": "Unlike on most salamanders_ this part of a newt is flat?",
        "Answer": "Tail",
        "A": "Head",
        "B": "Teeth",
        "C": "Feet",
        "D": "Tail"
    },
    {
        "Question": "What is the name of the family that the domestic cat is a member of?",
        "Answer": "Felidae",
        "A": "Felinae",
        "B": "Felis",
        "C": "Cat",
        "D": "Felidae"
    },
    {
        "Question": "What dog bread is one of the oldest breeds of dog and has flourished since before 400 BCE.",
        "Answer": "Pugs",
        "A": "Bulldogs",
        "B": "Boxers",
        "C": "Chihuahua",
        "D": "Pugs"
    },
    {
        "Question": "What is the name of the copper-rich protein that creates the blue blood in the Antarctic octopus?",
        "Answer": "Hemocyanin",
        "A": "Cytochrome",
        "B": "Iron",
        "C": "Methionine",
        "D": "Hemocyanin"
    },
    {
        "Question": "Which species is a 'mountain chicken'?",
        "Answer": "Frog",
        "A": "Chicken",
        "B": "Horse",
        "C": "Fly",
        "D": "Frog"
    },
    {
        "Question": "What is the fastest animal?",
        "Answer": "Peregrine Falcon",
        "A": "Golden Eagle",
        "B": "Cheetah",
        "C": "Horsefly",
        "D": "Peregrine Falcon"
    },
    {
        "Question": "The dish Fugu_ is made from what family of fish?",
        "Answer": "Pufferfish",
        "A": "Bass",
        "B": "Salmon",
        "C": "Mackerel",
        "D": "Pufferfish"
    },
    {
        "Question": "What is the collective noun for a group of crows?",
        "Answer": "Murder",
        "A": "Pack",
        "B": "Gaggle",
        "C": "Herd",
        "D": "Murder"
    },
    {
        "Question": "Which of the following is another name for the 'Poecilotheria Metallica Tarantula'?",
        "Answer": "Gooty",
        "A": "Hopper",
        "B": "Silver Stripe",
        "C": "Woebegone",
        "D": "Gooty"
    },
    {
        "Question": "What is the scientific name of the Common Chimpanzee?",
        "Answer": "Pan troglodytes",
        "A": "Gorilla gorilla",
        "B": "Pan paniscus",
        "C": "Panthera leo",
        "D": "Pan troglodytes"
    },
    {
        "Question": "By definition_ where does an abyssopelagic animal live?",
        "Answer": "At the bottom of the ocean",
        "A": "In the desert",
        "B": "On top of a mountain",
        "C": "Inside a tree",
        "D": "At the bottom of the ocean"
    },
    {
        "Question": "Which of these is a colony of polyps and not a jellyfish?",
        "Answer": "Portuguese Man-of-War",
        "A": "Sea Wasp",
        "B": "Irukandji",
        "C": "Sea Nettle",
        "D": "Portuguese Man-of-War"
    },
    {
        "Question": "What colour is the female blackbird?",
        "Answer": "Brown",
        "A": "Black",
        "B": "White",
        "C": "Yellow",
        "D": "Brown"
    },
    {
        "Question": "What is the common term for bovine spongiform encephalopathy (BSE)?",
        "Answer": "Mad Cow disease",
        "A": "Weil's disease",
        "B": "Milk fever",
        "C": "Foot-and-mouth disease",
        "D": "Mad Cow disease"
    },
    {
        "Question": "How many teeth does an adult rabbit have?",
        "Answer": "28",
        "A": "30",
        "B": "26",
        "C": "24",
        "D": "28"
    },
    {
        "Question": "What is the name for a male bee that comes from an unfertilized egg?",
        "Answer": "Drone",
        "A": "Soldier",
        "B": "Worker",
        "C": "Male",
        "D": "Drone"
    },
    {
        "Question": "What is the name of a rabbit's abode?",
        "Answer": "Burrow",
        "A": "Nest",
        "B": "Den",
        "C": "Dray",
        "D": "Burrow"
    },
    {
        "Question": "What is the collective noun for bears?",
        "Answer": "Sloth",
        "A": "Drove",
        "B": "Tribe",
        "C": "Husk",
        "D": "Sloth"
    },
    {
        "Question": "What is the collective noun for rats?",
        "Answer": "Mischief",
        "A": "Pack",
        "B": "Race",
        "C": "Drift",
        "D": "Mischief"
    },
    {
        "Question": "What is the collective noun for vultures?",
        "Answer": "Wake",
        "A": "Ambush",
        "B": "Building",
        "C": "Gaze",
        "D": "Wake"
    },
    {
        "Question": "What bird is born with claws on its wing digits?",
        "Answer": "Hoatzin",
        "A": "Cormorant",
        "B": "Cassowary",
        "C": "Secretary bird",
        "D": "Hoatzin"
    },
    {
        "Question": "Decapods' are an order of ten-footed crustaceans. Which of these are NOT decapods?",
        "Answer": "Krill",
        "A": "Lobsters",
        "B": "Shrimp",
        "C": "Crabs",
        "D": "Krill"
    }

];


var i = 0;
var j = 1;
var k = 0
for (name in Categories) {
    //    console.log(name);

    prop.forEach(it => {



        const cityRef = db.collection('Quiz').doc(name);



        const res = cityRef.collection("Set" + j).doc(QuestionsNum[i]).set({


            "A": it.A,
            "B": it.B,
            "C": it.C,
            "D": it.D,
            "Question": it.Question,
            "Answer": it.Answer,


        });

        /*
                console.log("Set" + j);
                console.log(it.D);
                console.log(QuestionsNum[i]);
        */

        i++;
        k++;
        if (k == 10) {
            k = 0;
            j++;
        }







    });
}
