$(document).ready(function () {
    $("form [data-validation][data-validation!='submit']").on("keyup change focus", function () {
        try {
            console.log(this);
            const value = $(this).val() || "";
            const validation_type = $(this).attr("data-validation");

            switch (validation_type) {
                case "no_numbers":
                    validate(this, value, /^[^0-9]+$/);
                    break;
                case "required":
                    validate(this, value, /^.+$/);
                    break;
                default:
                    console.warn("Wrong validation name.", validation_type);
                    break;
            }
        } catch (e) {
            console.error(e);
        }
    });


    function validate(element, value, regex) {

        // Test element value
        const isValid = regex.test(value);

        if (!isValid) {
            if ($(element).hasClass("valid-data")) {
                $(element).removeClass("valid-data");
            }

            if (!$(element).hasClass("invalid-data")) {
                $(element).addClass("invalid-data");
            }

        }
        else {
            if ($(element).hasClass("invalid-data")) {
                $(element).removeClass("invalid-data");
            }

            if (!$(element).hasClass("valid-data")) {
                $(element).addClass("valid-data");
            }

        }

        changeSubmitButtonStatus(element);
    }

    function changeSubmitButtonStatus(element) {
        // Disable/ enable button

        const closestForm = $(element).closest("form");
        if (!closestForm.length) {
            console.warn("Submit button not exists.");
            return;
        }

        const submitButton = $(closestForm).find("[data-validation='submit']");
        if (!submitButton.length) {
            console.warn("Submit button not exists.");
            return;
        }
        
        const allElements = $(closestForm).find("[data-validation][data-validation!='submit']").length;
        const validElements = $(closestForm).find(".valid-data").length;
        console.log(allElements, validElements);

        submitButton.attr("disabled", allElements !== validElements);
    }
})