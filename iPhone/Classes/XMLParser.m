#import "XMLParser.h"
#import "iPhoneAppDelegate.h"
#import "TrailPoint.h"

@implementation XMLParser

- (XMLParser *) initXMLParser {
    
	[super init];
	appDelegate = (iPhoneAppDelegate *)[[UIApplication sharedApplication] delegate];
	return self;
}

#pragma mark -
#pragma mark - Parser methods

- (void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName 
  namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qualifiedName attributes:(NSDictionary *)attributeDict {
	
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName 
  namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {	
    

}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string { 
  
}

#pragma mark -
#pragma mark - Dealloc

- (void) dealloc {
	[point release];
	[currentElementValue release];
	[super dealloc];
}

@end
