/**
 * A set of helper functions used by the application
 *
 * @type {{createSuccessResponse: module.exports.createSuccessResponse, createErrorResponse: module.exports.createErrorResponse, sendSuccessResponse: module.exports.sendSuccessResponse, sendErrorResponse: module.exports.sendErrorResponse}}
 */

module.exports = {
    createSuccessResponse: function(result) {
        return {status: 'success', result};
    },

    createErrorResponse: function(message) {
        return {status: 'error', message };
    },

    sendSuccessResponse: function(res, result) {
        res.send(this.createSuccessResponse(result));
    },

    sendErrorResponse: function(res, message) {
        res.send(this.createErrorResponse(message));
    }
};
