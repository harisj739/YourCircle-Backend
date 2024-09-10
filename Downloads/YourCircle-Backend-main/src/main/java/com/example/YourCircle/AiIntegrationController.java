// package com.example.YourCircle;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// // import org.springframework.ai.chat.ChatClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;


// import com.theokanning.openai.OpenAiService;
// import com.theokanning.openai.completion.CompletionRequest;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// // import org.springframework.ai.chat.client.ChatClient;
// import org.springframework.ai.chat.client.*;;

// @RestController
// public class AiIntegrationController {

//     private static final Logger LOGGER = LoggerFactory.getLogger(AiIntegrationController.class);
//     private final ChatClient chatClient;

//     public AiIntegrationController(ChatClient chatClient) {
//         this.chatClient = chatClient;
//     }

//     @GetMapping("/greeting")
//     public String greeting() {
//         LOGGER.info("Greeting request received");
//         final String aiResponse = chatClient.(
//                 """
//                 As a modern generative AI model,
//                 Generate a 5-liner greeting message in your style for a human.
//                 Thanks in advance.
//                 """
//         );
//         LOGGER.info("AI Response: {}", aiResponse);
//         return aiResponse;
//     }
// }


package com.example.YourCircle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionChoice;

import java.util.List;

@RestController
public class AiIntegrationController{  // define class AiIntegrationController

    private static final Logger LOGGER = LoggerFactory.getLogger(AiIntegrationController.class); //creates a logger, a logger is a tool that records messages such as error messeges and others 
    // private final ChatClient chatClient;
    private OpenAiService openAiService;

    // public AiIntegrationController(ChatClient chatClient){
    //     this.chatClient = chatClient;
    // }

    public AiIntegrationController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }
    
    //greeting endpoint was hit means that http request was made to /greeting endpoint of spring boot application.
    //endpoint means specifc url that can be accessed by clients or browerse postman curl
    //hit means that the external rewuest was succesful reached to the greeting enpoint 
    @GetMapping("/greeting")
    //mapping http get request to /greeting path to this method 
   
    public String greeting(){
        LOGGER.info("Greeting request received"); // logs the fact that the /greeting endpoint was hit, helps in tracing and debugging 
        
        // final String aiResponse = ((Object) chatClient).generate(
        //     // calling generate method to generate a response

        //     """
        //             Generate a 5-liner greeting 
        //             """
        // );

        // LOGGER.info("AI Response: {}", aiResponse);

        CompletionRequest request = CompletionRequest.builder()
            .prompt("Generate a 5 liner greeting message")
            .maxTokens(100) //limit the response length
            .build();
        
        //call the OpenAI API
        List<CompletionChoice> choice = openAiService.createCompletion(request).getChoices();
        String aiResponse = choice.get(0).getText(); //extracting response
        LOGGER.info("AI Response: {}", aiResponse);
        
        return aiResponse;
    }

}