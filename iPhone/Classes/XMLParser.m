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
	
	/*if([elementName isEqualToString:@"Books"]) {
		//Initialize the array.
		appDelegate.books = [[NSMutableArray alloc] init];
	}
	else if([elementName isEqualToString:@"Book"]) {
		
		//Initialize the book.
		aBook = [[Book alloc] init];
		
		//Extract the attribute here.
		aBook.bookID = [[attributeDict objectForKey:@"id"] integerValue];
		
		NSLog(@"Reading id value :%i", aBook.bookID);
	}*/
	
	NSLog(@"Element: %@", elementName);
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName 
  namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName {	
    
	if([elementName isEqualToString:@"Books"])
		return;
	if([elementName isEqualToString:@"Book"]) {
		[appDelegate.books addObject:aBook];
		
		[aBook release];
		aBook = nil;
	}
	else 
		[aBook setValue:currentElementValue forKey:elementName];
	
	[currentElementValue release];
	currentElementValue = nil;
}

- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string { 
    
	if(!currentElementValue) 
		currentElementValue = [[NSMutableString alloc] initWithString:string];
	else
		[currentElementValue appendString:string];
    
	NSLog(@"Processing Value: %@", currentElementValue);
}

#pragma mark -
#pragma mark - Dealloc

- (void) dealloc {
	
	[aBook release];
	[currentElementValue release];
	[super dealloc];
}

@end
