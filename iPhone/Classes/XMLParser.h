#import <UIKit/UIKit.h>

@class iPhoneAppDelegate, TrailPoint;

@interface XMLParser : NSObject <NSXMLParserDelegate> {
    
	NSMutableString *currentElementValue;
	iPhoneAppDelegate *appDelegate;
	TrailPoint *point; 
}

- (XMLParser *) initXMLParser;

@end
